package com.example.notes

import android.app.Application
import com.example.notes.data.NoteAppContainer
import com.example.notes.data.NoteAppDataContainer

class NotesApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: NoteAppContainer

    override fun onCreate() {
        super.onCreate()
        container = NoteAppDataContainer(this)
    }

}