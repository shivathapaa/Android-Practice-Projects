package com.shivathapaa.praticepath

import android.app.Application
import com.shivathapaa.praticepath.data.ObjectBoxDb

class NoteApp : Application() {

    // Initializing objectBox as soon as app starts
    override fun onCreate() {
        super.onCreate()
        ObjectBoxDb.init(this)
    }

}