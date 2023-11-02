package com.app.qfonapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.app.qfonapp.R
import com.app.qfonapp.base.QfonappActivity
import com.app.qfonapp.databinding.ActivitySplashBinding
import com.app.qfonapp.util.Config

@SuppressLint("CustomSplashScreen")
class SplashActivity : QfonappActivity() {

    private lateinit var b: ActivitySplashBinding

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this@SplashActivity, R.layout.activity_splash)
        init()
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        setText()
    }

    private fun setText() {
        b.lblTitle.text = getString(R.string.app_name_full)

        startsAppNormally()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun startsAppNormally() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, Config.TIMER_THREE.toLong())
    }
}