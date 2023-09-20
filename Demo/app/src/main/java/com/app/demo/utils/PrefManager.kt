package com.app.demo.utils

import android.content.Context
import android.content.Context.*
import android.content.SharedPreferences
import android.content.SharedPreferences.*
import com.app.demo.utils.Configs.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefManager @Inject constructor(@ApplicationContext context: Context) {

    private lateinit var editor: Editor
    private var fileName = "DemoPref"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(fileName, MODE_PRIVATE)

    /*fun clearForLogOut() {
        editor = sharedPreferences.edit()
        editor.remove(PREF_NAME).apply()
    }*/

    /*fun clear(Key: String?) {
        editor = sharedPreferences.edit()
        editor.remove(Key).apply()
    }*/

    /*fun clearAll() {
        editor = sharedPreferences.edit()
        editor.clear().apply()
    }*/

    var name: String?
        get() = sharedPreferences.getString(PREF_NAME, NULL)
        set(name) {
            editor = sharedPreferences.edit()
            editor.putString(PREF_NAME, name)
            editor.apply()
        }
}