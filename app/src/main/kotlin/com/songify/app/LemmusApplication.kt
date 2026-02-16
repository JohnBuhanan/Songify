package com.songify.app

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class SongifyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}
