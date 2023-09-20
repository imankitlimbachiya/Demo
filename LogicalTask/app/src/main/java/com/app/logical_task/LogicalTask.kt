package com.app.logical_task

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LogicalTask : Application() {

    override fun onCreate() {
        super.onCreate()
        logicalTask = this
    }

    companion object {

        private lateinit var logicalTask: LogicalTask

        fun context(): Context {
            return logicalTask.applicationContext
        }
    }
}