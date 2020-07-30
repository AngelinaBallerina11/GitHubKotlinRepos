package cz.angelina.kotlingithub.system

import android.app.Application
import cz.angelina.kotlingithub.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class KotlinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
