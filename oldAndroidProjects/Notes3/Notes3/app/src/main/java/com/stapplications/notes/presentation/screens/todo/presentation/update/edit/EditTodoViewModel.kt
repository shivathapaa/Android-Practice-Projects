package com.stapplications.notes.presentation.screens.todo.presentation.update.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.stapplications.notes.presentation.screens.todo.data.TodoUiState
import com.stapplications.notes.presentation.screens.todo.data.repository.TodoRepository

class EditTodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    var todoUiState: TodoUiState by mutableStateOf(TodoUiState())


}