package com.app.practical.activity

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.text.Html
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.practical.R
import com.app.practical.databinding.ActivityLoginBinding
import com.app.practical.model.Login
import com.app.practical.utils.NetworkResult
import com.app.practical.utils.PrefManager
import com.app.practical.viewModels.VmHome
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var m: ActivityLoginBinding
    private lateinit var p: PrefManager
    private val initOffset = 0
    private val waitSec = 1000
    private val vmHome by viewModels<VmHome>()
    private var login: Login? = null

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        m = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        init()
    }

    override fun onResume() {
        super.onResume()
        if (p.userId.isNotEmpty()) {
            val intent =
                Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vmHome.responseLogin.hasObservers()) vmHome.responseLogin.removeObservers(this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        p = PrefManager(this@LoginActivity)

        setText()

        m.btnLogin.setOnClickListener(listenerLogin)
    }

    private fun setText() {
        m.username.txtEt.hint = getString(R.string.username)
        m.username.txtEt.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        m.password.txtEt.hint = getString(R.string.password)
        m.password.txtEt.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        val text = "<font color=#FFFFFF>No account?</font> <font color=#000000>Create one.</font>"
        m.txtCreateAccount.text = Html.fromHtml(text, 0)
    }

    ///////////////////////////////////////////////////////////////////////////
    // ClickListener
    ///////////////////////////////////////////////////////////////////////////

    private var ctLogin = initOffset.toLong()
    private val listenerLogin = View.OnClickListener {
        if (SystemClock.elapsedRealtime() - ctLogin < waitSec) return@OnClickListener
        ctLogin = SystemClock.elapsedRealtime()
        if (p.userId.isNotEmpty()) {
            startActivity(
                Intent(this@LoginActivity, MainActivity::class.java)
            )
            finish()
        } else {
            requestLogin()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Requests
    ///////////////////////////////////////////////////////////////////////////

    private fun requestLogin() {
        vmHome.requestLogin(
            "fq@mailinator.com", "Test@123", "1", "1"
        )
        if (!vmHome.responseLogin.hasObservers()) {
            vmHome.responseLogin.observe(this@LoginActivity, observerLogin)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Observers
    ///////////////////////////////////////////////////////////////////////////

    private val observerLogin: androidx.lifecycle.Observer<NetworkResult<Login>> =
        androidx.lifecycle.Observer { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        try {
                            login = it
                            if (login != null) {
                                if (login!!.code == 200) {
                                    Toast.makeText(
                                        this@LoginActivity, "SUCCESS", Toast.LENGTH_LONG
                                    ).show()
                                    p.userId = ""
                                    p.accessToken = ""
                                    this@LoginActivity.p.userId = login!!.data.userId.toString()
                                    this@LoginActivity.p.accessToken = login!!.data.accessToken.toString()
                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@LoginActivity, "FAIL", Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    this@LoginActivity, "FAIL", Toast.LENGTH_LONG
                                ).show()
                                vmHome.responseLogin.removeObservers(this)
                            }
                        } catch (e: IOException) {
                            Toast.makeText(
                                this@LoginActivity, "FAIL", Toast.LENGTH_LONG
                            ).show()
                            vmHome.responseLogin.removeObservers(this)
                        }
                    }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(
                        this@LoginActivity, "FAIL", Toast.LENGTH_LONG
                    ).show()
                    vmHome.responseLogin.removeObservers(this)
                }

                is NetworkResult.Loading -> {}
            }
        }
}