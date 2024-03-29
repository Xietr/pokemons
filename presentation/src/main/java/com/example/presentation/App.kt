package com.example.presentation

import android.app.Application
import com.example.presentation.di.AppComponent
import com.example.presentation.di.AppModule
import com.example.presentation.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}