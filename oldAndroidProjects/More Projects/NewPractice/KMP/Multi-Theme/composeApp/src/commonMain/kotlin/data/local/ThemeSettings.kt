package data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import okio.Path.Companion.toPath

internal const val themeSettingDataStoreFileName = "theme_settings.preferences_pb"

/**
 * Gets the singleton DataStore instance, creating it if necessary.
 */
object ThemeSettings {
    private lateinit var dataStore: DataStore<Preferences>
    private val lock = SynchronizedObject()

    fun getDataStore(producedPath: () -> String): DataStore<Preferences> {
        return synchronized(lock) {
            if (::dataStore.isInitialized) {
                dataStore
            } else {
                PreferenceDataStoreFactory.createWithPath(
                    produceFile = { producedPath().toPath() }
                ).also { dataStore = it }
            }
        }
    }
}

// Google's Documentation Way
/*
 fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )
*/