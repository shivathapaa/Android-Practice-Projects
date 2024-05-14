package com.example.musicsample.feature.nav_bar_feature.home

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.CAMERA
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.musicsample.MusicSampleApplication

class MusicHomeScreenViewModel(
    application: Application,
) : AndroidViewModel(application) {
    // region ViewModel setup
    private val context: Context
        get() = getApplication()
    // The get() method ensures the context is retrieved only when needed, as opposed to storing it directly in a variable.

    data class UiState(
        val hasLocationAccess: Boolean,
        val hasCameraAccess: Boolean
    )

    var uiState by mutableStateOf(
        UiState(
            hasLocationAccess = hasPermission(ACCESS_COARSE_LOCATION),
            hasCameraAccess = hasPermission(CAMERA)
        )
    )
        private set

    private fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun onPermissionChange(permission: String, isGranted: Boolean) {
        when (permission) {
            ACCESS_COARSE_LOCATION -> {
                uiState = uiState.copy(hasLocationAccess = isGranted)
            }

            CAMERA -> {
                uiState = uiState.copy(hasCameraAccess = isGranted)
            }

            else -> {
                Log.e("Permission change", "Unexpected permission: $permission")
            }
        }
    }

    fun createSettingsIntent(): Intent {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            data = Uri.fromParts("package", context.packageName, null)
        }

        return intent
    }

}

class MusicHomeScreenViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val app =
            extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MusicSampleApplication
        return MusicHomeScreenViewModel(app) as T
    }
}