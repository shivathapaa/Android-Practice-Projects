package com.shivathapa.khaata.data.repository.category

import com.shivathapa.khaata.data.Category
import com.shivathapa.khaata.data.dao.CategoryDao
import com.shivathapa.khaata.data.CategoryWithExpenses
import kotlinx.coroutines.flow.Flow

class OfflineCategoryRepository(private val categoryDao: CategoryDao) : CategoryRepository {
    override suspend fun insertCategory(category: Category) = categoryDao.insertCategory(category)

    override suspend fun updateCategory(category: Category) = categoryDao.updateCategory(category)

    override suspend fun deleteCategory(category: Category) = categoryDao.deleteCategory(category)

    override fun getCategory(categoryId: Int): Flow<Category> = categoryDao.getCategory(categoryId)

    override fun getAllCategories(): Flow<List<Category>> = categoryDao.getAllCategories()

}