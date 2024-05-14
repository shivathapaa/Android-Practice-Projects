package com.example.musicsample.feature.nav_bar_feature.artist

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MusicArtistViewModel : ViewModel() {

    data class UiState(
        val selectedSingleImageUri: Uri? = null,
        val selectedMultipleImageUri: List<Uri> = emptyList()
    )

    private val _uiState = MutableStateFlow(UiState())

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun addUri(imageUri: Uri?) {
        _uiState.update { currentState ->
            currentState.copy(selectedSingleImageUri = imageUri)
        }
        // other actions for image
    }

    fun addUri(listOfImageUri: List<Uri>) {
        _uiState.update { currentState ->
            currentState.copy(selectedMultipleImageUri = listOfImageUri)
        }
        // other actions for image
    }
}
