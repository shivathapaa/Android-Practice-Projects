package com.shivathapa.khaata.ui.category.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shivathapa.khaata.data.Category
import com.shivathapa.khaata.data.repository.category.CategoryRepository
import com.shivathapa.khaata.ui.category.CategoryDetails
import com.shivathapa.khaata.ui.category.CategoryUiState

class CategoryEntryViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    /**
     * Holds current category ui state
     */
    var categoryUiState by mutableStateOf(CategoryUiState())
        private set


    fun updateCategoryUiState(categoryDetails: CategoryDetails) {
        categoryUiState =
            CategoryUiState(categoryDetails = categoryDetails, isEntryValid = validateInput(categoryDetails))
    }

    private fun validateInput(uiState: CategoryDetails = categoryUiState.categoryDetails): Boolean {
        return with(uiState) {
            category.isNotBlank()
        }
    }

    suspend fun saveCategory() {
        if (validateInput()) {
            categoryRepository.insertCategory(categoryUiState.categoryDetails.toCategory())
        }
    }

}

fun CategoryDetails.toCategory(): Category = Category(
    categoryId = categoryId,
    category = category,
//    date = date,
//    totalAmount = totalAmount
)

fun Category.toCategoryDetails(): CategoryDetails = CategoryDetails(
    categoryId = categoryId,
    category = category
)

fun Category.toCategoryUiState(isEntryValid: Boolean = false): CategoryUiState = CategoryUiState(
    categoryDetails = this.toCategoryDetails(),
    isEntryValid = isEntryValid
)