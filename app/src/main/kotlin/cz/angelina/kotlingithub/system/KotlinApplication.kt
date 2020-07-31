package cz.angelina.kotlingithub.system

import android.app.Application
import cz.angelina.kotlingithub.BuildConfig
import cz.angelina.kotlingithub.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.DebugTree

class KotlinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin {
            logger(AndroidLogger(Level.DEBUG))
            androidContext(this@KotlinApplication)
            modules(mainModule)
        }
    }
}
