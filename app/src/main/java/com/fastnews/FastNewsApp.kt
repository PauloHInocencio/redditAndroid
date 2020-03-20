package com.fastnews

import android.app.Application
import com.fastnews.di.interactorModule
import com.fastnews.di.networkModule
import com.fastnews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FastNewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@FastNewsApp)
            modules(listOf(networkModule, interactorModule, viewModelModule))
        }

    }
}