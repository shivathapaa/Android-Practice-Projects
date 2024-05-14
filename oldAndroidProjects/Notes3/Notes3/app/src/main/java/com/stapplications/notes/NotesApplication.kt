package com.stapplications.notes

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.stapplications.notes.presentation.screens.note.data.repository.LayoutPreferenceRepository
import com.stapplications.notes.search.NoteSSearchManager
import dagger.hilt.android.HiltAndroidApp

private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCE_NAME
)

@HiltAndroidApp
class NotesApplication : Application() {
    /* AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: NoteContainer
    lateinit var layoutPreferencesRepository: LayoutPreferenceRepository
    lateinit var noteSSearchManager: NoteSSearchManager

    override fun onCreate() {
        super.onCreate()
        container = NoteAppDataContainer(this)
        layoutPreferencesRepository = LayoutPreferenceRepository(dataStore)
        noteSSearchManager = NoteSSearchManager(this) // to use applicationContext
    }
}