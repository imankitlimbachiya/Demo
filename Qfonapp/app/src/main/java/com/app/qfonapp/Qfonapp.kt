package com.app.qfonapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Qfonapp : Application() {

    override fun onCreate() {
        super.onCreate()

        qfonapp = this
    }

    companion object {

        private lateinit var qfonapp: Qfonapp

        private fun context(): Context {
            return qfonapp.applicationContext
        }
    }
}