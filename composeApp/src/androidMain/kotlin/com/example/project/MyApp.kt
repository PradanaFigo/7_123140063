package com.example.project

import android.app.Application
import android.content.Context
import com.example.project.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import com.example.project.data.DatabaseDriverFactory
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)

            modules(appModule, module {
                single { DatabaseDriverFactory(get()).createDriver() }
                single<Settings> {
                    SharedPreferencesSettings(get<Context>().getSharedPreferences("settings", Context.MODE_PRIVATE))
                }
            })
        }
    }
}