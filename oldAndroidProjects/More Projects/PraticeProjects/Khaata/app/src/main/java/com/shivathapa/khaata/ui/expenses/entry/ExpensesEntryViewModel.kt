package com.shivathapa.khaata.ui.expenses.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shivathapa.khaata.data.Expense
import com.shivathapa.khaata.data.repository.expense.ExpenseRepository
import com.shivathapa.khaata.ui.expenses.ExpenseDetails
import com.shivathapa.khaata.ui.expenses.ExpensesUiState

class ExpensesEntryViewModel(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    var expensesUiState by mutableStateOf(ExpensesUiState())
        private set

    private fun validateInput(expenseUiState: ExpenseDetails = expensesUiState.expenseDetails): Boolean {
        return with(expenseUiState) {
            item.isNotBlank() && amount.isNotBlank()
        }
    }

    fun updateExpensesUiState(expenseDetails: ExpenseDetails) {
        expensesUiState =
            ExpensesUiState(
                expenseDetails = expenseDetails,
                isEntryValid = validateInput(expenseDetails)
            )
    }

    suspend fun saveExpense() {
        if (validateInput()) {
            expenseRepository.insertExpense(expensesUiState.expenseDetails.toExpense())
        }
    }
}

fun ExpenseDetails.toExpense(): Expense = Expense(
    expenseId = expenseId,
    expenseCategoryId = 0,
    item = item,
    currency = currency.toString(),
    volume = volume?.toDoubleOrNull() ?: 0.0,
    quantity = quantity?.toDoubleOrNull() ?: 0.0,
    unit = unit,
    amount = amount.toDoubleOrNull() ?: 0.0,
    date = "Date to"
)

fun Expense.toExpenseDetails(): ExpenseDetails = ExpenseDetails(
    expenseId = expenseId,
    expenseCategoryId = expenseCategoryId,
    item = item,
    currency = currency,
    volume = volume.toString(),
    quantity = quantity.toString(),
    unit = unit,
    amount = amount.toString(),
    date = date
)

fun Expense.toExpenseUiState(isEntryValid: Boolean = false): ExpensesUiState = ExpensesUiState(
    expenseDetails = this.toExpenseDetails(),
    isEntryValid = isEntryValid
)