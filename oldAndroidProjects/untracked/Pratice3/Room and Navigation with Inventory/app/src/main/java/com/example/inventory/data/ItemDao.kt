package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// DAOs are the main components of Room that are responsible for defining the interface that accesses the database.

// The DAO you create is a custom interface that provides convenience methods for querying/retrieving, inserting, deleting, and updating the database.
// Room generates an implementation of this class at compile time.

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)


    //The query now says to select all columns from the items, where the id matches the :id argument.
    // Notice the :id uses the colon notation in the query to reference arguments in the function.
    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    // Room keeps this Flow updated for you, which means you only need to explicitly get the data once.

    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

}