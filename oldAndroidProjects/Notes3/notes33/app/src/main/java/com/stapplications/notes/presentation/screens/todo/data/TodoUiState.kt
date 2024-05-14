package com.stapplications.notes.presentation.screens.todo.data

data class TodoUiState(
    val todoDetails: TodoDetails = TodoDetails(),
    val isUpdated: Boolean = false
)

data class TodoDetails(
    val todoId: Int = 0,
    val title: String = "",
    val body:  List<Pair<String, Boolean>> = listOf(Pair("", false)),
    val isDone: Boolean = false,
    val priority: Int = 0,
    val reminder: String? = null
)

fun TodoDetails.toTodo(): Todo = Todo(
    todoId = todoId,
    title = title,
    body = body,
    isDone = isDone,
    priority = priority,
    reminder = reminder
)

fun Todo.toTodoUiState(isUpdated: Boolean = true): TodoUiState = TodoUiState(
    todoDetails = this.toTodoDetails(),
    isUpdated = isUpdated
)

fun Todo.toTodoDetails(): TodoDetails = TodoDetails(
    todoId = todoId,
    title = title,
    body = body,
    isDone = isDone,
    priority = priority,
    reminder = reminder
)
