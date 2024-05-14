package com.shivathapa.khaata.data.repository.expense

import com.shivathapa.khaata.data.CategoryWithExpenses
import com.shivathapa.khaata.data.Expense
import com.shivathapa.khaata.data.dao.ExpenseDao
import kotlinx.coroutines.flow.Flow

class OfflineExpenseRepository(private val expenseDao: ExpenseDao) : ExpenseRepository {
    override suspend fun insertExpense(expense: Expense) = expenseDao.insertExpense(expense)

    override suspend fun updateExpense(expense: Expense) = expenseDao.updateExpense(expense)

    override suspend fun deleteExpense(expense: Expense) = expenseDao.deleteExpense(expense)

    override fun getExpense(expenseId: Int): Flow<Expense> = expenseDao.getExpense(expenseId)

    override fun getAllExpenses(): Flow<List<Expense>> = expenseDao.getAllExpenses()

    override fun getExpensesByCategoryIdWithTransaction(categoryId: Int):  Flow<CategoryWithExpenses> =
        expenseDao.getExpensesByCategoryIdWithTransaction(categoryId)

}