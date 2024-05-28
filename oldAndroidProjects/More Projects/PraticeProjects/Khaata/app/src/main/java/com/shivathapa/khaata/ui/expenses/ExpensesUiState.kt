package com.shivathapa.khaata.ui.expenses

data class ExpensesUiState (
    val expenseDetails: ExpenseDetails = ExpenseDetails(),
    val isEntryValid: Boolean = false
)

data class ExpenseDetails(
    val expenseId: Int = 0,
    val expenseCategoryId: Int = 0,
//    val category: String,
    val item: String = "",
    val currency: String? = "",
    val volume: String? = "",
    val quantity: String? = "",
    val unit: String? = "",
    val amount: String = "",
    val date: String? = ""
)