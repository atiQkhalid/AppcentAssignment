package com.example.appcentassignment

import android.app.Application
import android.content.Context
import com.example.appcentassignment.di.DIFramework

/**
 * The App.kt, Application class
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        DIFramework.init(this)
    }

    companion object {
        var instance: App? = null
        fun getAppContext(): Context {
            return instance as Context
        }
    }
}