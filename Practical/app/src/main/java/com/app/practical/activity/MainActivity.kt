package com.app.practical.activity

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.practical.R
import com.app.practical.adapter.EventAdapter
import com.app.practical.databinding.ActivityMainBinding
import com.app.practical.model.Event
import com.app.practical.utils.NetworkResult
import com.app.practical.utils.PrefManager
import com.app.practical.utils.Utils
import com.app.practical.viewModels.VmHome
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var m: ActivityMainBinding
    private lateinit var p: PrefManager
    private val vmHome by viewModels<VmHome>()
    private var event: Event? = null
    private var eventAdapter: EventAdapter? = null

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        m = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vmHome.responseEvent.hasObservers()) vmHome.responseEvent.removeObservers(this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        p = PrefManager(this@MainActivity)

        m.txtLogout.setOnClickListener(listenerLogout)

        if (Utils.isOnline(this@MainActivity)) {
            fetchEvent()
        } else {
            Toast.makeText(
                this@MainActivity,
                this@MainActivity.getString(R.string.no_internet),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // ClickListener
    ///////////////////////////////////////////////////////////////////////////

    private val listenerLogout = View.OnClickListener {
        if (p.userId.isNotEmpty()) {
            p.userId = ""
            p.accessToken = ""
            startActivity(
                Intent(this@MainActivity, LoginActivity::class.java)
            )
            finishAffinity()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Requests
    ///////////////////////////////////////////////////////////////////////////

    private fun fetchEvent() {
        m.rvEvent.adapter = null
        vmHome.fetchEvent(
            p.userId, "1", p.accessToken
        )
        if (!vmHome.responseEvent.hasObservers()) {
            vmHome.responseEvent.observe(this@MainActivity, observerEvent)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Observers
    ///////////////////////////////////////////////////////////////////////////

    private val observerEvent: androidx.lifecycle.Observer<NetworkResult<Event>> =
        androidx.lifecycle.Observer { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        try {
                            event = it
                            if (event != null) {
                                if (event!!.data.size > 0) {
                                    Toast.makeText(
                                        this@MainActivity, "SUCCESS", Toast.LENGTH_LONG
                                    ).show()
                                    eventAdapter = EventAdapter(this@MainActivity, event)
                                    m.rvEvent.adapter = eventAdapter
                                } else {
                                    Toast.makeText(
                                        this@MainActivity, "FAIL", Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                vmHome.responseEvent.removeObservers(this)
                                Toast.makeText(
                                    this@MainActivity, "FAIL", Toast.LENGTH_LONG
                                ).show()
                            }
                        } catch (e: IOException) {
                            vmHome.responseEvent.removeObservers(this)
                            Toast.makeText(
                                this@MainActivity, "FAIL", Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                is NetworkResult.Error -> {
                    vmHome.responseEvent.removeObservers(this)
                    Toast.makeText(
                        this@MainActivity, "FAIL", Toast.LENGTH_LONG
                    ).show()
                }

                is NetworkResult.Loading -> {}
            }
        }
}