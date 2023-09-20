package com.app.demo.base

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.app.demo.R
import dagger.hilt.android.scopes.ActivityRetainedScoped
import java.util.*
import javax.inject.Inject

@ActivityRetainedScoped
open class BaseDemo @Inject constructor(
    private var ctx: Context?, private var activity: Activity?
) {

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    fun showOffLine() {
        if (ctx != null) {
            Toast.makeText(
                ctx, ctx!!.getString(R.string.no_internet), Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun showToast(ctx: Context?, string: String) {
        if (ctx != null) {
            Toast.makeText(ctx, string, Toast.LENGTH_SHORT).show()
        }
    }
}