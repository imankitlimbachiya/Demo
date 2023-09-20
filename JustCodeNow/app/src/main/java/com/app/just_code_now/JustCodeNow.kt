package com.app.just_code_now

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JustCodeNow : Application() {

    override fun onCreate() {
        super.onCreate()
        justCodeNow = this
    }

    companion object {
        private lateinit var justCodeNow: JustCodeNow
    }
}