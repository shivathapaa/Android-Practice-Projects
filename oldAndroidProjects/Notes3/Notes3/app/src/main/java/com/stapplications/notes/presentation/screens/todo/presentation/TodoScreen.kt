package com.stapplications.notes.presentation.screens.todo.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stapplications.notes.NoteViewModelProvider
import com.stapplications.notes.R
import com.stapplications.notes.presentation.screens.todo.presentation.update.add.AddTodoScreen
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun TodoScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    openAddTodoDialog: Boolean,
    onAddTodoClose: () -> Unit,
    viewModel: TodoScreenViewModel = viewModel(factory = NoteViewModelProvider.Factory)
) {
    val todoHomeUiState by viewModel.todoUiState.collectAsState()

    if (todoHomeUiState.todoList.isNotEmpty()) {
        LazyColumn(
            modifier = modifier.padding(dimensionResource(id = R.dimen.small_padding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding)),
            contentPadding = innerPadding
        ) {
            items(items = todoHomeUiState.todoList, key = { todo -> todo.todoId }) { todo ->
                TodoCard(
                    title = todo.title,
                    todos = todo.body,
                    isDone = true,
                    onTitleCheckChange = { /*Todo*/ },
                    reminder = todo.reminder,
                    openAddTodoDialog = openAddTodoDialog,
                    onAddTodoClose = onAddTodoClose,
                )
            }
        }
    }
}

@Composable
fun TodoCard(
    modifier: Modifier = Modifier,
    title: String?,
    todos: List<Pair<String, Boolean>>,
    isDone: Boolean,
    onTitleCheckChange: (Boolean) -> Unit,
    reminder: String?,
    onAddTodoClose: () -> Unit,
    openAddTodoDialog: Boolean
) {

    Card(
        onClick = {
                  // Todo: open edit todo
//            openDialog.value = true
                  },
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.small_padding)),
            verticalArrangement = Arrangement.Center
        ) {
            if (!title.isNullOrBlank()) {
                Row(
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.small_padding)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = isDone, onCheckedChange = { onTitleCheckChange(it) })
                    Column {
                        Text(
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (!reminder.isNullOrBlank()) {
                            FormattedTimeAndDateText(dateTime = reminder)
                        }
                    }
                }
            }
            todos.forEach { todo ->
                if (todo.first.isNotBlank()) {
                    Row(
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.small_padding)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!title.isNullOrBlank()) {
                            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.large_padding)))
                        }
                        Checkbox(
                            checked = todo.second,
                            onCheckedChange = { /*TODO: Make pair*/ })
                        Column {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = todo.first,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium
                            )
                            if (!reminder.isNullOrBlank() && title.isNullOrBlank()) {
                                FormattedTimeAndDateText(dateTime = reminder)
                            }
                        }
                    }
                }
            }
        }
    }

    if (openAddTodoDialog) {
        AddTodoScreen(onDismiss = onAddTodoClose)
    }
}

@Composable
private fun FormattedTimeAndDateText(
    modifier: Modifier = Modifier,
    dateTime: String?
) {
    val formattedDateTime = getFormattedTime(dateTime)

    Row(
        modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.very_small_padding)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.very_small_padding))
    ) {
        Text(
            text = formattedDateTime.first,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outline
        )
        if (formattedDateTime.second) {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.todo_reminder_icon_size)),
                painter = painterResource(id = R.drawable.alarm_on),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.todo_reminder_icon_size)),
                painter = painterResource(id = R.drawable.alarm_off),
                contentDescription = null
            )
        }
    }
}

private fun getFormattedTime(localDateTime: String?): Pair<String, Boolean> {
    if (localDateTime.isNullOrBlank()) {
        return Pair("", false)

    } else {
        val formatterTime = DateTimeFormatter.ofPattern("h:mm a")
        val formatterDay = DateTimeFormatter.ofPattern("E h:mm a")
        val formatterWeek = DateTimeFormatter.ofPattern("MMMM d E h:mm a")
        val formatterYear = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a")

        val dateTime = LocalDateTime.parse(localDateTime)
        val now = LocalDateTime.now()
        val tomorrow = now.plusDays(1).toLocalDate()

        val isFutureDate = dateTime.isAfter(now)

        val formattedDateTime = when {
            dateTime.toLocalDate() == now.toLocalDate() -> dateTime.format(formatterTime)
            dateTime.toLocalDate() == tomorrow -> "Tomorrow " + dateTime.format(formatterTime)
            ChronoUnit.DAYS.between(now, dateTime) <= 7 -> dateTime.format(formatterWeek)
            ChronoUnit.YEARS.between(now, dateTime) >= 1 -> dateTime.format(formatterYear)
            else -> dateTime.format(formatterDay)
        }

        return Pair(formattedDateTime, isFutureDate)
    }
}