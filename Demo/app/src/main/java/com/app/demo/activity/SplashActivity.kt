package com.app.demo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.demo.R
import com.app.demo.databinding.ActivitySlashBinding
import com.app.demo.utils.Configs.T_THREE

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var m: ActivitySlashBinding

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        m = DataBindingUtil.setContentView(this@SplashActivity, R.layout.activity_slash)
        init()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        setText()
    }

    private fun setText() {
        m.lblTitle.text = getString(R.string.app_name)

        startsAppNormally()
    }

    private fun startsAppNormally() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, T_THREE.toLong())
    }
}