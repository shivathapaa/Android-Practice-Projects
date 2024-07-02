package com.shivathapaa.praticepath.presentation.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.shivathapaa.praticepath.R
import com.shivathapaa.praticepath.data.Note

@Composable
fun NotesListScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    listState: LazyListState = rememberLazyListState(),
    navigateToEdit: (Long) -> Unit,
    onDeleteClick: (Note) -> Unit,
    notes: List<Note>,
) {
    NoteList(
        modifier = modifier,
        innerPadding = innerPadding,
        listState = listState,
        notes = notes,
        openNote = navigateToEdit,
        onDeleteClick = onDeleteClick,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NoteList(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    listState: LazyListState,
    notes: List<Note>,
    openNote: (Long) -> Unit,
    onDeleteClick: (Note) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.small_padding))
//            .navigationBarsPadding()
            .animateContentSize(),
        contentPadding = innerPadding,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = notes, key = { note -> note.id }) { note ->
            SwipeBox(
                onDelete = { onDeleteClick(note) },
                onEdit = { openNote(note.id) },
                swipeEnabled = true,
                modifier = Modifier
                    .animateItemPlacement()
            ) {
                NoteListCard(
                    modifier = Modifier
                        .animateItemPlacement(),
                    title = note.title ?: "",
                    body = note.body ?: "",
                    dateTime = note.date ?: "",
                    openNote = { openNote(note.id) },
                    onDeleteClick = { onDeleteClick(note) }
                )
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SwipeBox(
    modifier: Modifier = Modifier,
    swipeEnabled: Boolean,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    content: @Composable () -> Unit
) {
    val swipeState = rememberSwipeToDismissBoxState()

    lateinit var icon: ImageVector
    lateinit var alignment: Alignment
    val color: Color

    when (swipeState.dismissDirection) {
        SwipeToDismissBoxValue.EndToStart -> {
            icon = Icons.Outlined.Delete
            alignment = Alignment.CenterEnd
            color = MaterialTheme.colorScheme.errorContainer
        }

        SwipeToDismissBoxValue.StartToEnd -> {
            icon = Icons.Outlined.Edit
            alignment = Alignment.CenterStart
            color =
                Color.Green.copy(alpha = 0.3f) // You can generate theme for successContainer in themeBuilder
        }

        SwipeToDismissBoxValue.Settled -> {
            icon = Icons.Outlined.Delete
            alignment = Alignment.CenterEnd
            color = MaterialTheme.colorScheme.errorContainer
        }
    }

    SwipeToDismissBox(
        modifier = modifier.animateContentSize(),
        enableDismissFromEndToStart = swipeEnabled,
        enableDismissFromStartToEnd = swipeEnabled,
        state = swipeState,
        backgroundContent = {
            if (swipeEnabled) {
                Box(
                    contentAlignment = alignment,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CardDefaults.shape)
                        .background(color)
                        .animateContentSize()
                ) {
                    Icon(
                        modifier = Modifier.minimumInteractiveComponentSize(),
                        imageVector = icon, contentDescription = null
                    )
                }
            }
        }
    ) {
        content()
    }

    when (swipeState.currentValue) {
        SwipeToDismissBoxValue.EndToStart -> {
            onDelete()
        }

        SwipeToDismissBoxValue.StartToEnd -> {
            LaunchedEffect(swipeState) {
                onEdit()
                swipeState.snapTo(SwipeToDismissBoxValue.Settled)
            }
        }

        SwipeToDismissBoxValue.Settled -> {
        }
    }
}

@Composable
fun NoteListCard(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    dateTime: String,
    openNote: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = openNote
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.small_padding)),
            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding))
        ) {
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.medium_padding)))
            BodyContent(
                modifier = Modifier.weight(1f),
                spacing = dimensionResource(id = R.dimen.very_small_padding),
                title = title,
                body = body,
                dateTime = dateTime
            )
//            DeleteIcon(onDeleteClick = onDeleteClick) // since swipe to dismiss is used
        }
    }
}

@Composable
private fun BodyContent(
    modifier: Modifier = Modifier,
    maxBodyLines: Int = 2,
    spacing: Dp,
    title: String,
    body: String,
    dateTime: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing),
    ) {
        if (title.isNotBlank()) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (body.isNotBlank()) {
            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = maxBodyLines,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Medium
            )
        } else {
            Text(
                text = stringResource(R.string.no_text),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = dateTime,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline,
            maxLines = 1,
            overflow = TextOverflow.Clip
        )
    }
}