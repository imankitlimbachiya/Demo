package com.app.qfonapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class QfonappActivity : AppCompatActivity() {

    private lateinit var qfonappBaseClass: QfonappBaseClass

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        qfonappBaseClass = QfonappBaseClass(this@QfonappActivity)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    fun showOffLine() {
        qfonappBaseClass.showOffLine()
    }

    fun showToast(string: String?) {
        qfonappBaseClass.showToast(string)
    }
}