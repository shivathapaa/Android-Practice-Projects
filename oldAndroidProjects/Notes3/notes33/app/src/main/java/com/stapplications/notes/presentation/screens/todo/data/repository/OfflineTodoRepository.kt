package com.stapplications.notes.presentation.screens.todo.data.repository

import com.stapplications.notes.presentation.screens.todo.data.Todo
import com.stapplications.notes.presentation.screens.todo.data.TodoDao
import kotlinx.coroutines.flow.Flow

class OfflineTodoRepository(private val todoDao: TodoDao) : TodoRepository {

    override fun getAllTodosStream(): Flow<List<Todo>> = todoDao.getAllTodos()

    override fun getTodoStream(id: Int): Flow<List<Todo>> = todoDao.getTodo(id = id)

    override suspend fun insertTodo(todo: Todo) = todoDao.insert(todo)

    override suspend fun deleteTodo(todo: Todo) = todoDao.delete(todo)

    override suspend fun updateTodo(todo: Todo) = todoDao.update(todo)

}