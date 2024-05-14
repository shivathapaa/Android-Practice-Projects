package com.stapplications.notes.presentation.screens.note.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class LayoutPreferenceRepository(
    private val dataStore: DataStore<Preferences>
) {

    private companion object {
        val IS_LINEAR_LAYOUT: Preferences.Key<Boolean> = booleanPreferencesKey("is_linear_layout")
        const val TAG = "LayoutPreferenceRepository"
    }

    // using DataStore.data property to expose DataStore values
    val isLinearLayout: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error while reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[IS_LINEAR_LAYOUT] ?: true
        }

    suspend fun saveLayoutPreference(isLinearLayout: Boolean) {
        // the value is defined and initialized here using edit()
        dataStore.edit { preferences ->
            preferences[IS_LINEAR_LAYOUT] = isLinearLayout
        }
    }

}