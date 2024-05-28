package com.example.dessertrelease.data

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

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    val isLinearLayout: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
// If a different type of exception is thrown, prefer re-throwing that exception. By emitting emptyPreferences() if there is an error, the map function can still map to the default value.
                throw it
            }
        }
        .map { preferences ->
        // This function accepts a lambda with the current Preferences object as a parameter. You can specify the key you previously defined to obtain the layout preference. Bear in mind that the value might not exist if saveLayoutPreference hasn't been called yet, so you must also supply a default value.
        preferences[IS_LINEAR_LAYOUT] ?: true
    }

    private companion object {
        val IS_LINEAR_LAYOUT = booleanPreferencesKey("is_linear_layout")
        const val TAG = "UserPreferencesRepo"
    }

    suspend fun saveLayoutPreference(isLinearLayout: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LINEAR_LAYOUT] = isLinearLayout
        }
    }
}