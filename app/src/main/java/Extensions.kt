package com.sensibol.android.base

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

val Any.displayName: String
    get() = "${javaClass.simpleName}[${hashCode().toString(16)}]"

internal fun ImageView.loadUrl(url: String, @DrawableRes placeholderResId: Int? = null) {
    val builder = Glide.with(this).load(url).dontAnimate()
    if (null != placeholderResId) builder.placeholder(placeholderResId)
    builder.listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            this@loadUrl.scaleType = ImageView.ScaleType.CENTER_CROP
            return false
        }

    })
        .into(this)

}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Exception>> LifecycleOwner.failure(liveData: L, body: (Exception?) -> Unit) =
    liveData.observe(this, Observer(body))

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(context)
