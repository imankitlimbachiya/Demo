package com.app.practical.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefManager @Inject constructor(@ApplicationContext context: Context) {

    private var fileName = "PracticalPreferences"

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(fileName, MODE_PRIVATE)

    private lateinit var editor: SharedPreferences.Editor

    var userId: String
        get() = sharedPreferences.getString(Configs.userId, "")!!
        set(userId) {
            editor = sharedPreferences.edit()
            editor.putString(Configs.userId, userId)
            editor.apply()
        }

    var accessToken: String
        get() = sharedPreferences.getString(Configs.accessToken, "")!!
        set(accessToken) {
            editor = sharedPreferences.edit()
            editor.putString(Configs.accessToken, accessToken)
            editor.apply()
        }
}