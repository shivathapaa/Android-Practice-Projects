package com.shivathapa.khaata.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.shivathapa.khaata.data.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM category WHERE categoryId = :categoryId")
    fun getCategory(categoryId: Int): Flow<Category>

    @Query("SELECT * FROM category ORDER BY categoryId ASC")
    fun getAllCategories(): Flow<List<Category>>

}