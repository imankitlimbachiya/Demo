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
import com.app.just_code_now.databinding.FragmentWorkBinding
import com.app.just_code_now.model.Photo
import com.app.just_code_now.utils.NetworkResult
import com.app.just_code_now.utils.Utils
import com.app.just_code_now.viewModel.VmHome
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import java.io.IOException

@AndroidEntryPoint
class WorkFragment : BaseFragment() {

    private lateinit var b: FragmentWorkBinding

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_work, container, doNot)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            initActionbar()
            setText()
        }
    }

    private fun initActionbar() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.actionBar.lblTitle.text = getString(R.string.to_do)

            b.actionBar.imgBack.setOnClickListener { activity!!.finish() }
        }
    }

    private fun setText() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.lblTitle.text = getString(R.string.coming_soon)
        }
    }
}