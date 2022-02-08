package com.android.base.com.nasapictures

import android.app.Application
import com.sensibol.android.base.displayName
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class NasaImageApp : Application() {
    override fun onCreate() {
        Timber.v("onCreate $displayName")
        super.onCreate()
    }

}