package com.app.just_code_now.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private lateinit var base: JustCodeNowBase

    companion object {
        const val doNot = false
        const val do_ = true
    }

    ///////////////////////////////////////////////////////////////////////////
    // OverRide Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            base = JustCodeNowBase(requireActivity())
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    fun showOffLine() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            base.showOffLine()
        }
    }

    fun showToast(string: String) {
        base.showToast(string)
    }
}