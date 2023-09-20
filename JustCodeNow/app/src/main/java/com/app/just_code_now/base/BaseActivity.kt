package com.app.just_code_now.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private lateinit var base: JustCodeNowBase

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        base = JustCodeNowBase(this@BaseActivity)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    fun showOffLine(ctx: Context?) {
        if (ctx != null) {
            base.showOffLine()
        }
    }

    fun showToast(ctx: Context?, string: String) {
        if (ctx != null) {
            Toast.makeText(ctx, string, Toast.LENGTH_SHORT).show()
        }
    }
}