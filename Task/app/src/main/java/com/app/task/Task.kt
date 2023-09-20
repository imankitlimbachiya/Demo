package com.app.task

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Task : Application() {

    override fun onCreate() {
        super.onCreate()
        task = this
    }

    companion object {

        private lateinit var task: Task

        /*private fun context(): Context {
            return task.applicationContext
        }*/
    }
}