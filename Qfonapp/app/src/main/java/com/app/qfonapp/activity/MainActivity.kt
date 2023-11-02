package com.app.qfonapp.activity

import android.os.Bundle
import android.os.SystemClock
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.qfonapp.R
import com.app.qfonapp.base.QfonappActivity
import com.app.qfonapp.databinding.ActivityMainBinding
import com.app.qfonapp.dialog.AddPollFragment
import com.app.qfonapp.event.AddPollEvent
import com.app.qfonapp.fragment.CurrentPollFragment
import com.app.qfonapp.fragment.PollHistoryFragment
import com.app.qfonapp.util.Config.ONE
import com.app.qfonapp.util.Config.TIMER_ONE
import com.app.qfonapp.util.Config.ZERO
import com.app.qfonapp.util.Config.do_
import com.google.android.material.navigation.NavigationBarView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : QfonappActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var b: ActivityMainBinding
    private val fm: FragmentManager = supportFragmentManager
    private var fCurrentPoll: Fragment? = null
    private var fPollHistory: Fragment? = null
    private var selectedFragment = ZERO
    private var active: Fragment? = null
    private var ctAddPoll = ZERO.toLong()

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        init()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAddPollEvent(event: AddPollEvent) {
        fCurrentPoll = CurrentPollFragment()
        selectedFragment = ZERO
        active = fCurrentPoll
        if (!fm.isDestroyed) {
            fm.beginTransaction()
                .add(R.id.fragment_container, fCurrentPoll as CurrentPollFragment, "1")
                .commit()
        }

        b.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_poll_selected)
        b.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_history)
    }

    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                when (selectedFragment) {
                    ZERO -> {
                        finish()
                    }

                    ONE -> {
                        b.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_poll_selected)
                        b.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_history)
                        if (active != null && fCurrentPoll != null) {
                            fm.beginTransaction().hide(active!!).show(fCurrentPoll!!).commit()
                            active = fCurrentPoll
                            selectedFragment = ZERO
                        }
                        b.navigation.selectedItemId = R.id.nav_current_poll
                    }
                }
            }
        }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return onNavItemClicked(item)
    }

    private fun onNavItemClicked(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_current_poll -> {

                fCurrentPoll = CurrentPollFragment()
                selectedFragment = ZERO
                active = fCurrentPoll
                if (!fm.isDestroyed) {
                    fm.beginTransaction()
                        .add(R.id.fragment_container, fCurrentPoll as CurrentPollFragment, "1")
                        .commit()
                }

                b.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_poll_selected)
                b.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_history)

                do_
            }

            R.id.nav_poll_history -> {

                fPollHistory = PollHistoryFragment()
                selectedFragment = ONE
                active = fPollHistory
                if (!fm.isDestroyed) {
                    fm.beginTransaction()
                        .add(R.id.fragment_container, fPollHistory as PollHistoryFragment, "2")
                        .commit()
                }

                b.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_poll)
                b.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_history_selected)

                do_
            }

            else -> throw IllegalStateException("IllegalStateException : " + item.itemId)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        onBackPressedDispatcher.addCallback(this@MainActivity, onBackPressedCallback)

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        fCurrentPoll = CurrentPollFragment()
        selectedFragment = ZERO
        active = fCurrentPoll
        if (!fm.isDestroyed) {
            fm.beginTransaction()
                .add(R.id.fragment_container, fCurrentPoll as CurrentPollFragment, "1")
                .commit()
        }

        b.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_poll_selected)
        b.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_history)

        b.navigation.setOnItemSelectedListener(this)
        b.navigation.itemIconTintList = null

        b.fabAddPoll.setOnClickListener(listenerFabAddPoll)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Listener
    ///////////////////////////////////////////////////////////////////////////

    private val listenerFabAddPoll = View.OnClickListener {
        if (SystemClock.elapsedRealtime() - ctAddPoll < TIMER_ONE) {
            return@OnClickListener
        }
        ctAddPoll = SystemClock.elapsedRealtime()
        if (!isDestroyed && !isFinishing && !supportFragmentManager.isDestroyed) {
            val addPollFragment = AddPollFragment()
            addPollFragment.show(fm, addPollFragment.javaClass.name)
        }
    }
}