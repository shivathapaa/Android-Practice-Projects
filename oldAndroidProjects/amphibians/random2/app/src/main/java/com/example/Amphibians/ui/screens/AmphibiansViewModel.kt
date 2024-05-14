package com.example.Amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.Amphibians.AmphibianApplication
import com.example.Amphibians.ui.data.AmphibianRepository
import com.example.Amphibians.ui.model.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibianUiState {
    data class Success(val amphibians: List<Amphibian>): AmphibianUiState
    object Error: AmphibianUiState
    object Loading: AmphibianUiState
}

class HomeScreenViewModel(
    // the value of the constructor parameter comes from the application container because the app is now using dependency injection.
    private val amphibianRepository: AmphibianRepository
) : ViewModel() {

    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibiansDetail()
    }

    fun getAmphibiansDetail() {
        viewModelScope.launch {
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState =
                try {
                    AmphibianUiState.Success(amphibianRepository.getAmphibians())
                } catch (e: IOException) {
                    AmphibianUiState.Error
                } catch (e: HttpException) {
                    AmphibianUiState.Error
                }
        }
    }

    //    A companion object helps us by having a single instance of an object that is used by everyone without needing to create a new instance of an expensive object.
//    This is an implementation detail, and separating it lets us make changes without impacting other parts of the app's code.
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianApplication)
                val amphibianRepository = application.container.amphibianRepository
                HomeScreenViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }

}