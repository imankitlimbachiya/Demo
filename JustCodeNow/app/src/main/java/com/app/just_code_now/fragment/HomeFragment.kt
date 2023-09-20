package com.app.just_code_now.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.app.just_code_now.R
import com.app.just_code_now.activity.PostActivity
import com.app.just_code_now.adapter.PostAdapter
import com.app.just_code_now.base.BaseFragment
import com.app.just_code_now.databinding.FragmentHomeBinding
import com.app.just_code_now.interfaces.PostInterface
import com.app.just_code_now.model.Post
import com.app.just_code_now.utils.NetworkResult
import com.app.just_code_now.utils.Utils
import com.app.just_code_now.viewModel.VmHome
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import java.io.IOException

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var b: FragmentHomeBinding
    private val vmHome by viewModels<VmHome>()
    private val postList: MutableList<Post> = ArrayList()
    private var postAdapter: PostAdapter? = null

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, doNot)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vmHome.responsePost.hasObservers())
            vmHome.responsePost.removeObservers(this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {

            initActionbar()

            if (Utils.isOnline(activity)) {
                fetchPosts()
            } else {
                showOffLine()
            }
        }
    }

    private fun initActionbar() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.actionBar.lblTitle.text = getString(R.string.home)

            b.actionBar.imgBack.setOnClickListener { activity!!.finish() }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun showLoading() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.cpiLoading.visibility = View.VISIBLE
            b.rvPost.visibility = View.GONE
        }
    }

    private fun showData() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.cpiLoading.visibility = View.GONE
            b.rvPost.visibility = View.VISIBLE
            b.rvPost.adapter = null
            postAdapter = PostAdapter(activity!!, postList, callBack)
            b.rvPost.adapter = postAdapter
        }
    }

    private fun showNoData() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.cpiLoading.visibility = View.GONE
            b.rvPost.visibility = View.GONE
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Requests
    ///////////////////////////////////////////////////////////////////////////

    private fun fetchPosts() {
        showLoading()
        vmHome.fetchPost()
        if (!vmHome.responsePost.hasObservers()) {
            vmHome.responsePost.observe(viewLifecycleOwner, observerPostList)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Observers
    ///////////////////////////////////////////////////////////////////////////

    private val observerPostList: androidx.lifecycle.Observer<NetworkResult<MutableList<Post>>> =
        androidx.lifecycle.Observer { response ->
            if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let {
                            try {
                                postList.addAll(it)
                                if (postList.size > 0) {
                                    showData()
                                } else {
                                    showToast(getString(R.string.no_data_found))
                                }
                            } catch (e: IOException) {
                                vmHome.responsePost.removeObservers(this)
                            } catch (e: JSONException) {
                                vmHome.responsePost.removeObservers(this)
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        vmHome.responsePost.removeObservers(this)
                        showNoData()
                    }

                    is NetworkResult.Loading -> {}
                }
            }
        }

    ///////////////////////////////////////////////////////////////////////////
    // Callbacks
    ///////////////////////////////////////////////////////////////////////////

    private val callBack: PostInterface = PostInterface { data, _ ->
        startActivity(
            Intent(
                requireActivity(), PostActivity::class.java
            ).putExtra(
                "postId", data.id
            ).putExtra(
                "post", data.title
            )
        )
    }
}