package cz.angelina.kotlingithub.app

import android.app.Application
import cz.angelina.kotlingithub.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class KotlinApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
