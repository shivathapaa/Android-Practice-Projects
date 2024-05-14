package com.shivathapa.khaata.data.repository.category

import com.shivathapa.khaata.data.Category
import com.shivathapa.khaata.data.CategoryWithExpenses
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    // Category
    suspend fun insertCategory(category: Category)

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    fun getCategory(categoryId: Int): Flow<Category>

    fun getAllCategories(): Flow<List<Category>>

}
