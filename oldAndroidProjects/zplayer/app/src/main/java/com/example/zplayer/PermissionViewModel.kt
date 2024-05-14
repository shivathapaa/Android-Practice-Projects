package com.example.zplayer

import android.Manifest.permission.READ_MEDIA_AUDIO
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel

class PermissionViewModel(private val context: Context) : ViewModel() {

    data class UiState(
        val hasAudioAccess: Boolean
    )

    var uiState by mutableStateOf(
        UiState(
            hasAudioAccess = checkPermission(READ_MEDIA_AUDIO)
        )
    )

    fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun onPermissionChange(permission: String, isGranted: Boolean) {
        when (permission) {
            READ_MEDIA_AUDIO -> {
                uiState = uiState.copy(hasAudioAccess = isGranted)
            }

            else -> {
                Log.e("Permission change", "Unexpected permission: $permission")
            }
        }
    }
}