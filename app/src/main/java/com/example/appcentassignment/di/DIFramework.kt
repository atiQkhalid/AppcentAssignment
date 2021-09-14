package com.example.appcentassignment.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * DIFramework.kt
 * The dependency injection framework used by the app.
 * uses Koin for DI.
 */
object DIFramework {

    fun init(context: Context) {
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(context)
            val repoModule = module {
                // single
            }
            // declare modules
            modules(repoModule)
        }
    }
}