package com.app.just_code_now.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.just_code_now.R
import com.app.just_code_now.base.BaseActivity
import com.app.just_code_now.base.BaseFragment.Companion.do_
import com.app.just_code_now.databinding.ActivityHomeBinding
import com.app.just_code_now.fragment.HomeFragment
import com.app.just_code_now.fragment.PhotoFragment
import com.app.just_code_now.fragment.WorkFragment
import com.app.just_code_now.utils.Configs.ONE
import com.app.just_code_now.utils.Configs.THREE
import com.app.just_code_now.utils.Configs.TWO
import com.app.just_code_now.utils.Configs.ZERO
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var b: ActivityHomeBinding
    private val fm: FragmentManager = supportFragmentManager

    private var fHome: Fragment? = null
    private var fPhoto: Fragment? = null
    private var fWork: Fragment? = null
    private var selectedFragment = ONE
    private var active: Fragment? = null
    private var currentActiveFragment = ONE

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this@HomeActivity, R.layout.activity_home)
        init()
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {

        fHome = HomeFragment()
        fPhoto = null
        fWork = null
        selectedFragment = ONE
        active = fHome
        if (!fm.isDestroyed) {
            fm.beginTransaction().add(R.id.fragment_container, fHome as HomeFragment, "1").commit()
        }

        b.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_home_selected)
        b.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_photo)
        b.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_work)

        b.navigation.setOnItemSelectedListener(this)
        b.navigation.itemIconTintList = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return onNavItemClicked(item)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun onNavItemClicked(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                fPhoto = null
                fWork = null
                fHome = HomeFragment()

                selectedFragment = ONE
                active = fHome
                if (!fm.isDestroyed) {
                    fm.beginTransaction().add(R.id.fragment_container, fHome as HomeFragment, "1").commit()
                }
                b.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_home_selected)
                b.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_photo)
                b.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_work)
                return do_
            }
            R.id.nav_photo -> {
                fHome = null
                fWork = null
                fPhoto = PhotoFragment()

                selectedFragment = TWO
                active = fPhoto
                if (!fm.isDestroyed) {
                    fm.beginTransaction().add(R.id.fragment_container, fPhoto as PhotoFragment, "2").commit()
                }
                b.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_home)
                b.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_photo_selected)
                b.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_work)
                return do_
            }
            R.id.nav_toDo -> {
                fHome = null
                fPhoto = null
                fWork = WorkFragment()
                selectedFragment = THREE
                active = fWork
                if (!fm.isDestroyed) {
                    fm.beginTransaction().add(R.id.fragment_container, fWork as WorkFragment, "3").commit()
                }
                b.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_home)
                b.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_photo)
                b.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_work_selected)
                return do_
            }
            else -> throw IllegalStateException("IllegalStateException : " + item.itemId)
        }
    }
}