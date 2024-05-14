package com.stapplications.notes.presentation.screens.todo.data.repository

import com.stapplications.notes.presentation.screens.todo.data.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getAllTodosStream(): Flow<List<Todo>>

    fun getTodoStream(id: Int): Flow<List<Todo>>

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

}