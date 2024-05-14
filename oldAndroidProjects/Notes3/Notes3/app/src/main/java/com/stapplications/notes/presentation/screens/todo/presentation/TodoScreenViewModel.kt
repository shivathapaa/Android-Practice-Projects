package com.stapplications.notes.presentation.screens.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stapplications.notes.presentation.screens.todo.data.Todo
import com.stapplications.notes.presentation.screens.todo.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoScreenViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _todoUiState: MutableStateFlow<TodoHomeUiState> =
        MutableStateFlow(TodoHomeUiState())
    val todoUiState: StateFlow<TodoHomeUiState> = _todoUiState.asStateFlow()

    init {
        viewModelScope.launch {
            todoRepository.getAllTodosStream().collect { todos ->
                _todoUiState.update { currentState ->
                    currentState.copy(todoList = todos)
                }
            }
        }
    }

    fun updateTodoCompletedState(todo: Todo, isCompleted: Boolean) {
        viewModelScope.launch {
//            todoRepository.updateTodo()
        }
    }
}

data class TodoHomeUiState(val todoList: List<Todo> = listOf())