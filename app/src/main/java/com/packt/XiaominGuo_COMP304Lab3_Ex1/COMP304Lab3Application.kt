package com.packt.XiaominGuo_COMP304Lab3_Ex1

import android.app.Application
import com.packt.XiaominGuo_COMP304Lab3_Ex1.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class COMP304Lab3Application: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            workManagerFactory()
            modules(appModules)
        }
    }
}