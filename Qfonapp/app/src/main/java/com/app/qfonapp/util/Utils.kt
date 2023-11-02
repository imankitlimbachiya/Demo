package com.app.qfonapp.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Utils {

    @JvmStatic
    fun getTime(format: String?): String {
        return SimpleDateFormat(format, Locale.ENGLISH).format(Calendar.getInstance().time)
    }
}