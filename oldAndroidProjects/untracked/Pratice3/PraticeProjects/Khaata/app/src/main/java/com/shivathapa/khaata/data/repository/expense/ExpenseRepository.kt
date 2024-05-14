package com.shivathapa.khaata.data.repository.expense

import com.shivathapa.khaata.data.CategoryWithExpenses
import com.shivathapa.khaata.data.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    // Expense

    fun getAllExpenses(): Flow<List<Expense>>

    fun getExpense(expenseId: Int): Flow<Expense>

    suspend fun deleteExpense(expense: Expense)

    suspend fun insertExpense(expense: Expense)

    suspend fun updateExpense(expense: Expense)

    fun getExpensesByCategoryIdWithTransaction(categoryId: Int):  Flow<CategoryWithExpenses>
}