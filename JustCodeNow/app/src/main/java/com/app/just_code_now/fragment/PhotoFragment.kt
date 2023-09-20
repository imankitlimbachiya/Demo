package com.app.just_code_now.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.app.just_code_now.R
import com.app.just_code_now.adapter.PhotoAdapter
import com.app.just_code_now.base.BaseFragment
import com.app.just_code_now.databinding.FragmentPhotoBinding
import com.app.just_code_now.model.Photo
import com.app.just_code_now.utils.NetworkResult
import com.app.just_code_now.utils.Utils
import com.app.just_code_now.viewModel.VmHome
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import java.io.IOException

@AndroidEntryPoint
class PhotoFragment : BaseFragment() {

    private lateinit var b: FragmentPhotoBinding
    private val vmHome by viewModels<VmHome>()
    private val photoList: MutableList<Photo> = ArrayList()
    private var photoAdapter: PhotoAdapter? = null

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, doNot)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vmHome.responsePhoto.hasObservers())
            vmHome.responsePhoto.removeObservers(this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {

            initActionbar()

            if (Utils.isOnline(activity)) {
                fetchPhotos()
            } else {
                showOffLine()
            }
        }
    }

    private fun initActionbar() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.actionBar.lblTitle.text = getString(R.string.photo)

            b.actionBar.imgBack.setOnClickListener { activity!!.finish() }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun showLoading() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.cpiLoading.visibility = View.VISIBLE
            b.rvPhoto.visibility = View.GONE
        }
    }

    private fun showData() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.cpiLoading.visibility = View.GONE
            b.rvPhoto.visibility = View.VISIBLE
            b.rvPhoto.adapter = null
            photoAdapter = PhotoAdapter(activity!!, photoList)
            b.rvPhoto.adapter = photoAdapter
        }
    }

    private fun showNoData() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.cpiLoading.visibility = View.GONE
            b.rvPhoto.visibility = View.GONE
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Requests
    ///////////////////////////////////////////////////////////////////////////

    private fun fetchPhotos() {
        showLoading()
        vmHome.fetchPhoto()
        if (!vmHome.responsePhoto.hasObservers()) {
            vmHome.responsePhoto.observe(viewLifecycleOwner, observerPhotoList)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Observers
    ///////////////////////////////////////////////////////////////////////////

    private val observerPhotoList: androidx.lifecycle.Observer<NetworkResult<MutableList<Photo>>> =
        androidx.lifecycle.Observer { response ->
            if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let {
                            try {
                                photoList.addAll(it)
                                if (photoList.size > 0) {
                                    showData()
                                } else {
                                    showToast(getString(R.string.no_data_found))
                                }
                            } catch (e: IOException) {
                                vmHome.responsePhoto.removeObservers(this)
                            } catch (e: JSONException) {
                                vmHome.responsePhoto.removeObservers(this)
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        vmHome.responsePhoto.removeObservers(this)
                        showNoData()
                    }

                    is NetworkResult.Loading -> {}
                }
            }
        }
}