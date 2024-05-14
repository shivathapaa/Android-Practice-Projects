package com.stapplications.notes.presentation.screens.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todos WHERE todoId = :id")
    fun getTodo(id: Int): Flow<List<Todo>>

    @Query("SELECT * FROM todos ORDER BY todoId DESC")
    fun getAllTodos(): Flow<List<Todo>>

}