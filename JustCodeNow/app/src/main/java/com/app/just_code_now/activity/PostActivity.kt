package com.app.just_code_now.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.app.just_code_now.R
import com.app.just_code_now.adapter.CommentAdapter
import com.app.just_code_now.base.BaseActivity
import com.app.just_code_now.databinding.ActivityPostBinding
import com.app.just_code_now.model.Comment
import com.app.just_code_now.utils.NetworkResult
import com.app.just_code_now.utils.Utils
import com.app.just_code_now.viewModel.VmHome
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import java.io.IOException

@AndroidEntryPoint
class PostActivity : BaseActivity() {

    private lateinit var b: ActivityPostBinding
    private val vmHome by viewModels<VmHome>()
    private val commentList: MutableList<Comment> = ArrayList()
    private var commentAdapter: CommentAdapter? = null

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this@PostActivity, R.layout.activity_post)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vmHome.responseComment.hasObservers())
            vmHome.responseComment.removeObservers(this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        val postId = intent.getStringExtra("postId")
        val post = intent.getStringExtra("post")

        initActionbar()
        setText(post)

        if (Utils.isOnline(this@PostActivity)) {
            fetchComments(postId)
        } else {
            showOffLine(this@PostActivity)
        }
    }

    private fun initActionbar() {
        b.actionBar.lblTitle.text = getString(R.string.comment)

        b.actionBar.imgBack.setOnClickListener { finish() }
    }

    private fun setText(post: String?) {
        b.lblTitle.text = post
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun showLoading() {
        b.cpiLoading.visibility = View.VISIBLE
        b.rvComment.visibility = View.GONE
    }

    private fun showData() {
        b.cpiLoading.visibility = View.GONE
        b.rvComment.visibility = View.VISIBLE
        b.rvComment.adapter = null
        commentAdapter = CommentAdapter(this@PostActivity, commentList)
        b.rvComment.adapter = commentAdapter
    }

    private fun showNoData() {
        b.cpiLoading.visibility = View.GONE
        b.rvComment.visibility = View.GONE
    }

    ///////////////////////////////////////////////////////////////////////////
    // Requests
    ///////////////////////////////////////////////////////////////////////////

    private fun fetchComments(postId: String?) {
        showLoading()
        vmHome.fetchComment(postId)
        if (!vmHome.responseComment.hasObservers()) {
            vmHome.responseComment.observe(this, observerCommentList)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Observers
    ///////////////////////////////////////////////////////////////////////////

    private val observerCommentList: androidx.lifecycle.Observer<NetworkResult<MutableList<Comment>>> =
        androidx.lifecycle.Observer { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        try {
                            commentList.addAll(it)
                            if (commentList.size > 0) {
                                showData()
                            } else {
                                showToast(this@PostActivity, getString(R.string.no_data_found))
                            }
                        } catch (e: IOException) {
                            vmHome.responseComment.removeObservers(this)
                        } catch (e: JSONException) {
                            vmHome.responseComment.removeObservers(this)
                        }
                    }
                }

                is NetworkResult.Error -> {
                    vmHome.responseComment.removeObservers(this)
                    showNoData()
                }

                is NetworkResult.Loading -> {}
            }
        }
}