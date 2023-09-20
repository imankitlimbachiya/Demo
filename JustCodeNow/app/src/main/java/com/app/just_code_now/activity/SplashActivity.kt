package com.app.just_code_now.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.app.just_code_now.R
import com.app.just_code_now.databinding.ActivitySplashBinding
import com.app.just_code_now.utils.Configs.T_THREE

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

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
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        setText()
    }

    private fun setText() {
        b.lblTitle.text = getString(R.string.app_name)

        startsAppNormally()
    }

    private fun startsAppNormally() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }, T_THREE.toLong())
    }
}