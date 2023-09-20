package com.app.demo.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.demo.R
import com.app.demo.base.BaseFragment.Companion.do_
import com.app.demo.base.DemoActivity
import com.app.demo.databinding.ActivityHomeBinding
import com.app.demo.fragment.HomeFragment
import com.app.demo.utils.Configs.*
import com.app.demo.utils.PrefManager
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : DemoActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var m: ActivityHomeBinding
    private lateinit var p: PrefManager
    private val fm: FragmentManager = supportFragmentManager

    private var fHome: Fragment? = null
    private var selectedFragment = ONE
    private var active: Fragment? = null
    private var currentActiveFragment = ONE

    ///////////////////////////////////////////////////////////////////////////
    // Base Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        m = DataBindingUtil.setContentView(this, R.layout.activity_home)
        init()
    }

    override fun onBackPressed() {
        when (currentActiveFragment) {
            ONE -> {
                finish()
            }
            TWO, THREE, FOUR, FIVE -> {
                m.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_nav_un_wishlist_png)
                m.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_nav_un_scan_png)
                m.navigation.menu.getItem(THREE).setIcon(R.drawable.ic_nav_un_collection_png)
                m.navigation.menu.getItem(FOUR).setIcon(R.drawable.ic_nav_un_profile_png)
                if (active != null && fHome != null) {
                    fm.beginTransaction().hide(active!!).show(fHome!!).commit()
                    active = fHome
                    currentActiveFragment = ONE
                }
                m.navigation.selectedItemId = R.id.nav_home
            }
            else -> super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return onNavItemClicked(item)
    }

    private fun onNavItemClicked(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                m.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_nav_home_png)
                m.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_nav_un_wishlist_png)
                m.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_nav_un_scan_png)
                m.navigation.menu.getItem(THREE).setIcon(R.drawable.ic_nav_un_collection_png)
                m.navigation.menu.getItem(FOUR).setIcon(R.drawable.ic_nav_un_profile_png)
                return do_
            }
            R.id.nav_wishlist -> {
                m.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_nav_un_home_png)
                m.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_nav_wishlist_png)
                m.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_nav_un_scan_png)
                m.navigation.menu.getItem(THREE).setIcon(R.drawable.ic_nav_un_collection_png)
                m.navigation.menu.getItem(FOUR).setIcon(R.drawable.ic_nav_un_profile_png)
                return do_
            }
            R.id.nav_scan -> {
                m.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_nav_un_home_png)
                m.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_nav_un_wishlist_png)
                m.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_nav_scan_png)
                m.navigation.menu.getItem(THREE).setIcon(R.drawable.ic_nav_un_collection_png)
                m.navigation.menu.getItem(FOUR).setIcon(R.drawable.ic_nav_un_profile_png)
                return do_
            }
            R.id.nav_collection -> {
                m.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_nav_un_home_png)
                m.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_nav_un_wishlist_png)
                m.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_nav_un_scan_png)
                m.navigation.menu.getItem(THREE).setIcon(R.drawable.ic_nav_collection_png)
                m.navigation.menu.getItem(FOUR).setIcon(R.drawable.ic_nav_un_profile_png)
                return do_
            }
            R.id.nav_profile -> {
                m.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_nav_un_home_png)
                m.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_nav_un_wishlist_png)
                m.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_nav_un_scan_png)
                m.navigation.menu.getItem(THREE).setIcon(R.drawable.ic_nav_un_collection_png)
                m.navigation.menu.getItem(FOUR).setIcon(R.drawable.ic_nav_profile_png)
                return do_
            }
            else -> throw IllegalStateException("IllegalStateException : " + item.itemId)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        p = PrefManager(this@HomeActivity)

        fHome = HomeFragment()
        selectedFragment = ONE
        active = fHome
        if (!fm.isDestroyed) {
            fm.beginTransaction().add(R.id.fragment_container, fHome as HomeFragment, "1").commit()
        }

        m.navigation.menu.getItem(ZERO).setIcon(R.drawable.ic_nav_home_png)
        m.navigation.menu.getItem(ONE).setIcon(R.drawable.ic_nav_un_wishlist_png)
        m.navigation.menu.getItem(TWO).setIcon(R.drawable.ic_nav_un_scan_png)
        m.navigation.menu.getItem(THREE).setIcon(R.drawable.ic_nav_un_collection_png)
        m.navigation.menu.getItem(FOUR).setIcon(R.drawable.ic_nav_un_profile_png)

        m.navigation.setOnItemSelectedListener(this)
        m.navigation.itemIconTintList = null
    }
}