package com.example.movieapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    val testString = ""

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var appInstance: Context
    }
}