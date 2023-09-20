package com.app.demo.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.demo.utils.FontDemo
import com.app.demo.utils.PrefManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    lateinit var prefManager: PrefManager
    lateinit var base: BaseDemo
    lateinit var dFont: FontDemo

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
            prefManager = PrefManager(requireActivity())
            dFont = FontDemo(activity)
            base = BaseDemo(requireContext(), requireActivity())
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

    fun showToast(ctx: Context?, string: String) {
        base.showToast(ctx, string)
    }
}