package com.app.demo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.TextUtils
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object Utils {

    @JvmStatic
    fun isOnline(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw)
        return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH))
    }

    @JvmStatic
    fun setCircularImage(
        ctx: Context?, p: String?, i: ImageView?, @DrawableRes ph: Int, isCacheEnable: Boolean
    ) {
        if (ctx != null) {
            if (!TextUtils.isEmpty(p)) {
                Glide.with(ctx)
                    .load(p)
                    .diskCacheStrategy(if (isCacheEnable) DiskCacheStrategy.AUTOMATIC else DiskCacheStrategy.NONE)
                    .skipMemoryCache(!isCacheEnable)
                    .transition(DrawableTransitionOptions.withCrossFade(750))
                    .centerInside()
                    .apply(RequestOptions().error(ph).circleCrop().centerCrop())
                    .circleCrop()
                    .placeholder(ph)
                    .into(i!!)
            }
        }
    }
}