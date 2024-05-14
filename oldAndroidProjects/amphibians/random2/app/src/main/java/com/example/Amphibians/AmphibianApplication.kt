package com.example.Amphibians

import android.app.Application
import com.example.Amphibians.ui.data.AppContainer
import com.example.Amphibians.ui.data.DefaultAppContainer

class AmphibianApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}