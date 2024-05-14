package com.example.zplayer.ui.permission

import android.Manifest.permission.READ_MEDIA_AUDIO
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel

class MediaAudioPermissionViewModel(
    application: Application
): AndroidViewModel(application = application) {

    private val context: Context
        get() = getApplication()

    data class MediaAudioUiState(
        val hasMediaAudioPermission: Boolean
    )

    var mediaAudioUiState by mutableStateOf(
        MediaAudioUiState(
            hasMediaAudioPermission = hasPermission(READ_MEDIA_AUDIO)
        )
    )
        private set

    fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}