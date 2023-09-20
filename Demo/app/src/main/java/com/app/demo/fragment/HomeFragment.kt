package com.app.demo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.app.demo.R
import com.app.demo.activity.OtpActivity
import com.app.demo.adapter.AvailableAdapter
import com.app.demo.base.BaseFragment
import com.app.demo.databinding.FragmentHomeBinding
import com.app.demo.interfaces.AvailableInterface
import com.app.demo.model.Available
import com.app.demo.utils.Configs.ONE
import com.app.demo.utils.Configs.ZERO
import com.app.demo.utils.FontDemo
import com.app.demo.utils.NetworkResult
import com.app.demo.utils.PrefManager
import com.app.demo.utils.Utils
import com.app.demo.viewModels.VmHome
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var m: FragmentHomeBinding
    private val vmHome by viewModels<VmHome>()
    private var adapter: AvailableAdapter? = null
    private var available: Available? = null
    private var isLoading = doNot
    private var isLast = doNot

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        m = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, doNot)
        return m.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vmHome.responseAvailable.hasObservers())
            vmHome.responseAvailable.removeObservers(this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            prefManager = PrefManager(requireActivity())

            setText()

            m.optionScan.lytRelative.setOnClickListener {
                val intent = Intent(activity, OtpActivity::class.java)
                startActivity(intent)
            }

            if (Utils.isOnline(activity)) {
                fetchAvailableList()
            } else {
                showOffLine()
            }
        }
    }

    private fun setText() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {

            dFont.setFont(m.actionBar.lblTitle, FontDemo.POPPINS_BOLD)

            m.actionBar.lblTitle.text = getString(R.string.my_dashboard)
            m.optionScan.lblTitle.text = getString(R.string.scan)
            m.optionScan.lblDescription.text = getString(R.string.scan_description)
            m.optionSell.lblTitle.text = getString(R.string.sell)
            m.optionSell.lblDescription.text = getString(R.string.sell_description)
            m.available.lblTitle.text = getString(R.string.available_to_buy)
        }
    }

    private fun showLoading() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            m.cpiLoading.visibility = View.VISIBLE
            m.available.rv.visibility = View.GONE
        }
    }

    private fun showData() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            showToast(activity, getString(R.string.data_successfully_loaded))
            m.cpiLoading.visibility = View.GONE
            m.available.rv.visibility = View.VISIBLE
            m.available.rv.adapter = null
            adapter = AvailableAdapter(activity, available, callBack)
            m.available.rv.adapter = adapter
        }
    }

    private fun showNoData() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            m.cpiLoading.visibility = View.GONE
            m.available.rv.visibility = View.GONE
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Requests
    ///////////////////////////////////////////////////////////////////////////

    private fun fetchAvailableList() {
        showLoading()
        if (available != null) available!!.data.list.clear()
        isLoading = doNot
        isLast = doNot
        m.available.rv.adapter = null
        vmHome.fetchAvailableList()
        if (!vmHome.responseAvailable.hasObservers()) {
            vmHome.responseAvailable.observe(viewLifecycleOwner, observerAvailableList)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Observers
    ///////////////////////////////////////////////////////////////////////////

    private val observerAvailableList: androidx.lifecycle.Observer<NetworkResult<Available>> =
        androidx.lifecycle.Observer { response ->
            if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let {
                            try {
                                isLoading = doNot
                                available = it
                                if (available != null && available!!.result == ONE) {
                                    adapter = AvailableAdapter(activity, available, callBack)
                                    m.available.rv.adapter = adapter
                                    if (available!!.data.list.size > ZERO) {
                                        showData()
                                    } else {
                                        showNoData()
                                    }
                                } else {
                                    vmHome.responseAvailable.removeObservers(this)
                                    showNoData()
                                }
                            } catch (e: IOException) {
                                vmHome.responseAvailable.removeObservers(this)
                                showNoData()
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        vmHome.responseAvailable.removeObservers(this)
                        showNoData()
                    }

                    is NetworkResult.Loading -> {}
                }
            }
        }

    ///////////////////////////////////////////////////////////////////////////
    // Callbacks
    ///////////////////////////////////////////////////////////////////////////

    private val callBack: AvailableInterface = AvailableInterface { _, _ -> }
}