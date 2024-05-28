package com.shivathapa.khaata.ui.category

data class CategoryUiState(
    val categoryDetails: CategoryDetails =  CategoryDetails(),
    val isEntryValid: Boolean = false
)

data class CategoryDetails(
    val categoryId: Int = 0,
    val category: String = "",
//    val date: Date = Date(day = 1, month = 1, year = 2000 ),
//    val totalAmount: Double = 0.0
)
