package com.app.qfonapp.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class QfonappFragment : Fragment() {

    private lateinit var qfonappBaseClass: QfonappBaseClass

    companion object {
        const val doNot_ = false
        const val do_ = true
    }

    ///////////////////////////////////////////////////////////////////////////
    // OverRide Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            qfonappBaseClass = QfonappBaseClass(requireActivity())
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    fun showOffLine() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            qfonappBaseClass.showOffLine()
        }
    }

    fun showToast(string: String) {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            qfonappBaseClass.showToast(string)
        }
    }
}