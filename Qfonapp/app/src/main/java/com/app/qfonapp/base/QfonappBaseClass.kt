package com.app.qfonapp.base

import android.content.Context
import android.widget.Toast
import com.app.qfonapp.R
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
open class QfonappBaseClass @Inject constructor(private var ctx: Context?) {

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    fun showOffLine() {
        if (ctx != null) {
            Toast.makeText(ctx, ctx!!.getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
        }
    }

    fun showToast(string: String?) {
        if (ctx != null) {
            Toast.makeText(ctx, string, Toast.LENGTH_SHORT).show()
        }
    }
}