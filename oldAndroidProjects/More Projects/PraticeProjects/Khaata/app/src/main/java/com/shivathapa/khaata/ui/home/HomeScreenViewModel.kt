package com.shivathapa.khaata.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivathapa.khaata.data.Category
import com.shivathapa.khaata.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeScreenViewModel(categoryRepository: CategoryRepository) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = categoryRepository.getAllCategories().map { HomeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val categoryList: List<Category> = listOf())