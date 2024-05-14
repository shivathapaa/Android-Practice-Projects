package com.shivathapa.khaata.data

import android.content.Context
import com.shivathapa.khaata.data.repository.category.CategoryRepository
import com.shivathapa.khaata.data.repository.category.OfflineCategoryRepository
import com.shivathapa.khaata.data.repository.expense.ExpenseRepository
import com.shivathapa.khaata.data.repository.expense.OfflineExpenseRepository
/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val expenseRepository: ExpenseRepository
    val categoryRepository: CategoryRepository
}

/**
 * [AppContainer] implementation that provides instance of [Repository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    override val categoryRepository: CategoryRepository by lazy {
        OfflineCategoryRepository(KhaataDatabase.getDatabase(context).categoryDao())
    }

    override val expenseRepository: ExpenseRepository by lazy {
        OfflineExpenseRepository(KhaataDatabase.getDatabase(context).expenseDao())
    }
}