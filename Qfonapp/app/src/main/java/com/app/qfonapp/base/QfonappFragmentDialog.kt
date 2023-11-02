package com.app.qfonapp.base

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.app.qfonapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class QfonappFragmentDialog : DialogFragment() {

    private lateinit var qfonappBaseClass: QfonappBaseClass
    private var savedInstanceState: Bundle? = null

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            qfonappBaseClass = QfonappBaseClass(requireActivity())
        }
    }

    override fun getTheme(): Int {
        return if (savedInstanceState == null) R.style.HomeBaseDialogAnimation else R.style.HomeBaseDialogWithOutAnimation
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    fun showOffline() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            qfonappBaseClass.showOffLine()
        }
    }

    fun showToast(message: String?) {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }
}