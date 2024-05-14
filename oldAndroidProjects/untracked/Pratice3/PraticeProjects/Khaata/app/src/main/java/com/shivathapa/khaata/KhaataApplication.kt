package com.shivathapa.khaata

import android.app.Application
import com.shivathapa.khaata.data.AppContainer
import com.shivathapa.khaata.data.AppDataContainer

class KhaataApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}