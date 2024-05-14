package com.stapplications.notes.presentation.screens.todo.presentation.update.add

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.stapplications.notes.presentation.screens.todo.data.Todo
import com.stapplications.notes.presentation.screens.todo.data.TodoDetails
import com.stapplications.notes.presentation.screens.todo.data.TodoUiState
import com.stapplications.notes.presentation.screens.todo.data.repository.TodoRepository
import com.stapplications.notes.presentation.screens.todo.data.toTodo

class AddTodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    var todoUiState: TodoUiState by mutableStateOf(TodoUiState())
        private set

    fun updateUiState(todoDetails: TodoDetails) {
        todoUiState = TodoUiState(todoDetails = todoDetails, isUpdated = true)
    }

    fun removeListItem(index: Int) {
        val newList = todoUiState.todoDetails.copy(body = todoUiState.todoDetails.body.toMutableList().apply {
            this.removeAt(index)
        })
        updateUiState(newList)
    }

    fun updateListItem(index: Int, todo: String) {
        val newList = todoUiState.todoDetails.copy(body = todoUiState.todoDetails.body.toMutableList().apply {
            this[index] = Pair(todo, false)
        })
        updateUiState(newList)
    }

    fun addListItem(index: Int, todo: String) {
        val newList = todoUiState.todoDetails.copy(body = todoUiState.todoDetails.body.toMutableList().apply {
            this.add(index, Pair(todo, false))
        })
        updateUiState(newList)
    }

    suspend fun saveTodo(): Boolean {
        return if (todoUiState.todoDetails.body.first().first.isNotBlank() || todoUiState.todoDetails.title.isNotBlank()){
            Log.d("CheckBeforeSavingTitleAndBody", todoUiState.todoDetails.title)
            Log.d("CheckBeforeSavingTitleAndBody", todoUiState.todoDetails.body.first().first)
            todoRepository.insertTodo(todoUiState.todoDetails.toTodo())
            todoUiState = TodoUiState()
            true
        } else {
            Log.d("CheckBeforeSavingTitleAndBody", "Bypassed empty")
            false
        }
    }

    suspend fun deleteTodo(todo: Todo) {
        todoRepository.deleteTodo(todo = todo)
    }

}