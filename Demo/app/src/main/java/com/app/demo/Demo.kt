package com.app.demo

import android.app.Application
import android.content.Context
import com.app.demo.utils.PrefManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Demo : Application() {

    override fun onCreate() {
        super.onCreate()
        demo = this
    }

    companion object {

        private lateinit var demo: Demo

        private fun context(): Context {
            return demo.applicationContext
        }

        fun pefManager(): PrefManager {
            return PrefManager(context())
        }
    }
}