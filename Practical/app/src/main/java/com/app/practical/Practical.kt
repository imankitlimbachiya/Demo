package com.app.practical

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Practical : Application() {

    override fun onCreate() {
        super.onCreate()
        practical = this
    }

    companion object {

        private lateinit var practical: Practical

        private fun context(): Context {
            return practical.applicationContext
        }
    }
}