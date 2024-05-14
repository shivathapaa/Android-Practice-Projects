package com.stapplications.notes.presentation.screens.todo.presentation.update.edit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stapplications.notes.presentation.screens.todo.presentation.TodoScreen

@Composable
fun EditTodoScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    openEditTodoDialog: Boolean,
    onEditTodoClose: () -> Unit,
) {
    TodoScreen(
        modifier = modifier,
        innerPadding = innerPadding,
        openAddTodoDialog = openEditTodoDialog,
        onAddTodoClose = onEditTodoClose
    )
}