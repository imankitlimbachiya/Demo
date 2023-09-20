package com.app.demo.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.demo.utils.FontDemo
import com.app.demo.utils.PrefManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class DemoActivity : AppCompatActivity() {

    private lateinit var base: BaseDemo
    private lateinit var pref: PrefManager
    private lateinit var dFont: FontDemo

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dFont = FontDemo(this@DemoActivity)
        pref = PrefManager(this@DemoActivity)
        base = BaseDemo(this@DemoActivity, this@DemoActivity)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    fun showToast(ctx: Context?, string: String) {
        if (ctx != null) {
            Toast.makeText(ctx, string, Toast.LENGTH_SHORT).show()
        }
    }
}