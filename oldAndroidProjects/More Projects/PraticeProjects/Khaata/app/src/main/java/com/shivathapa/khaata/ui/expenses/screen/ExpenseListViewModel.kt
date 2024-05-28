package com.shivathapa.khaata.ui.expenses.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivathapa.khaata.data.CategoryWithExpenses
import com.shivathapa.khaata.data.Expense
import com.shivathapa.khaata.data.repository.expense.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

class ExpenseListViewModel(
    savedStateHandle: SavedStateHandle,
    expenseRepository: ExpenseRepository
) : ViewModel() {
    private val categoryId: Int = checkNotNull(savedStateHandle[ExpensesDestination.categoryIdArg])

    private val expensesByCategoryId = expenseRepository.getExpensesByCategoryIdWithTransaction(categoryId)

    var expenseListUiState: StateFlow<ExpenseListUiState> =
        expensesByCategoryId
            .map {
                it.toExpenseListUiState(expensesByCategoryId)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ExpenseListUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class ExpenseListUiState(
    val expenseList: List<Expense> = listOf()
)

fun CategoryWithExpenses.toExpenseListUiState(expensesByCategoryId: Flow<CategoryWithExpenses>): ExpenseListUiState {
    val currentExpenses = runBlocking {
        expensesByCategoryId.first()
    }

    return ExpenseListUiState(
        expenseList = currentExpenses.expenses
    )
}