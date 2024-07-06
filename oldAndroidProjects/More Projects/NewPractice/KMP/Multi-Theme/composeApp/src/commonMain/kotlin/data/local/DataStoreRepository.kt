package data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class DataStoreRepository(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val SELECTED_THEME_COLOR_KEY = stringPreferencesKey("selected_theme_color")
        val IS_DYNAMIC_COLOR_SET_KEY = booleanPreferencesKey("is_dynamic_color_set")
        val IS_DARK_MODE_SET_KEY = booleanPreferencesKey("is_dark_mode_set")
    }

    suspend fun saveSelectedThemeColor(selectedThemeColor: String): Boolean =
        try {
            dataStore.edit { preferences ->
                preferences.set(key = SELECTED_THEME_COLOR_KEY, value = selectedThemeColor)
            }
            true
        } catch (e: Exception) {
            println("Preferences error: saveThemeColor. Error: $e")
            false
        }

    suspend fun saveIsDynamicColorSet(isDynamicColorSet: Boolean): Boolean =
        try {
            dataStore.edit { preferences ->
                preferences.set(key = IS_DYNAMIC_COLOR_SET_KEY, value = isDynamicColorSet)
            }
            true
        } catch (e: Exception) {
            println("Preferences error: saveIsDynamicColorSet. Error: $e")
            false
        }

    suspend fun saveIsDarkModeSet(isDarkModeSet: Boolean): Boolean =
        try {
            dataStore.edit { preferences ->
                preferences.set(key = IS_DARK_MODE_SET_KEY, value = isDarkModeSet)
            }
            true
        } catch (e: Exception) {
            println("Preferences error: saveIsDarkModeSet. Error: $e")
            false
        }

    fun readSelectedThemeColor(): Flow<String> =
        dataStore.data
            .catch { emptyFlow<String>() }
            .map { preferences ->
                preferences[SELECTED_THEME_COLOR_KEY] ?: "PINK"
            }

    fun readIsDynamicColorSet(): Flow<Boolean> =
        dataStore.data
            .catch { emptyFlow<Boolean>() }
            .map { preferences ->
                preferences[IS_DYNAMIC_COLOR_SET_KEY] ?: false
            }

    fun readIsDarkModeSet(): Flow<Boolean> =
        dataStore.data
            .catch {
                println("Something is worng pref")
                emptyFlow<Boolean>() }
            .map { preferences ->
                println("pref read: inside: ")
                preferences[IS_DARK_MODE_SET_KEY] ?: false
            }
}