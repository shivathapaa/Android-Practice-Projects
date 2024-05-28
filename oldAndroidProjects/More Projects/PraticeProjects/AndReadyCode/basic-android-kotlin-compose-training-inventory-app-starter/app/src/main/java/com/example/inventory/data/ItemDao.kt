package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query(value = "SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>
    //It is recommended to use Flow in the persistence layer. With Flow as the return type, you receive notification whenever the data in the database changes.
    // Because of the Flow return type, Room also runs the query on the background thread. You don't need to explicitly make it a suspend function and call it inside a coroutine scope.

    @Query(value = "SELECT * FROM items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}