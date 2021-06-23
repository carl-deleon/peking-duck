package com.scrrrt.kompoze

import android.app.Application
import timber.log.Timber

class KompozeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}