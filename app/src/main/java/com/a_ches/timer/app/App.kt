package com.a_ches.timer.app

import android.app.Application
import com.a_ches.timer.di.mainActivityViewModel
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(mainActivityViewModel)
        }
    }
}