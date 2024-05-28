package com.shivathapa.khaata.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.shivathapa.khaata.data.CategoryWithExpenses
import com.shivathapa.khaata.data.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expense WHERE expenseId = :expenseId")
    fun getExpense(expenseId: Int): Flow<Expense>

    @Query("SELECT * FROM expense ORDER BY expenseId ASC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Transaction
    @Query("SELECT * FROM category WHERE categoryId = :categoryId")
    fun getExpensesByCategoryIdWithTransaction(categoryId: Int):  Flow<CategoryWithExpenses>
//    fun getExpensesByCategoryId(categoryId: Int): Flow<List<Expense>>
}