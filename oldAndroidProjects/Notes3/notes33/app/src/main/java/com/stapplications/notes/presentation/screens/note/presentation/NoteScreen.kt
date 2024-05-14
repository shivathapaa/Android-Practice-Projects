package com.stapplications.notes.presentation.screens.note.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.stapplications.notes.R
import com.stapplications.notes.presentation.home.LayoutUiState
import com.stapplications.notes.presentation.home.getFormattedTime
import com.stapplications.notes.presentation.screens.note.data.Note
import kotlinx.coroutines.launch

@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    notes: List<Note>,
    gridState: LazyStaggeredGridState,
    listState: LazyListState,
    layoutUiState: LayoutUiState,
    onSelectLayout: (Boolean) -> Unit,
    navigateToEdit: (Int) -> Unit,
    onDeleteClick: (Note) -> Unit,
    onPickImage: (Int, Uri?) -> Unit
) {
    Crossfade(
        targetState = layoutUiState.isLinearLayout,
        animationSpec = tween(durationMillis = 500),
        label = "AnimateListAndGrid"
    ) { showGrid ->
        when (showGrid) {
            true -> NoteGrid(
                modifier = modifier,
                innerPadding = innerPadding,
                notes = notes,
                gridState = gridState,
                openNote = navigateToEdit,
                onDeleteClick = onDeleteClick,
                selectLayout = onSelectLayout,
                layoutUiState = layoutUiState
            )

            false -> NoteList(
                modifier = modifier,
                innerPadding = innerPadding,
                listState = listState,
                notes = notes,
                openNote = navigateToEdit,
                onDeleteClick = onDeleteClick,
                onPickImage = onPickImage,
                selectLayout = onSelectLayout,
                layoutUiState = layoutUiState
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NoteGrid(
    modifier: Modifier = Modifier,
    swipeEnabled: Boolean = true,
    innerPadding: PaddingValues,
    gridState: LazyStaggeredGridState,
    notes: List<Note>,
    openNote: (Int) -> Unit,
    onDeleteClick: (Note) -> Unit,
    selectLayout: (Boolean) -> Unit,
    layoutUiState: LayoutUiState
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.small_padding))
//            .navigationBarsPadding()
            .animateContentSize(),
        contentPadding = innerPadding,
        state = gridState,
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = dimensionResource(id = R.dimen.extra_small_padding),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding)),
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            NoteCountWithListPreference(
                modifier = Modifier.fillMaxWidth(),
                noteCount = notes.size,
                selectLayout = selectLayout,
                isLinearLayout = layoutUiState.isLinearLayout,
                iconRes = layoutUiState.toggleIcon,
                contentDescriptionRes = layoutUiState.toggleContentDescription
            )
        }

        items(items = notes, key = { note -> note.id }) { note ->
            SwipeBox(
                swipeEnabled = swipeEnabled,
                onDelete = { onDeleteClick(note) },
                onEdit = { openNote(note.id) },
                modifier = Modifier
                    .animateItemPlacement()
            ) {
                NoteGridCard(
                    modifier = Modifier
                        .animateItemPlacement()
                        .fillMaxWidth(),
                    title = note.title.removeExtraSpacesAndLines(),
                    body = note.body.removeExtraSpacesAndLines(),
                    dateTime = getFormattedTime(note.dateTime),
                    openNote = { openNote(note.id) },
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NoteList(
    modifier: Modifier = Modifier,
    swipeEnabled: Boolean = true,
    innerPadding: PaddingValues,
    listState: LazyListState,
    notes: List<Note>,
    openNote: (Int) -> Unit,
    onDeleteClick: (Note) -> Unit,
    onPickImage: (Int, Uri?) -> Unit,
    selectLayout: (Boolean) -> Unit,
    layoutUiState: LayoutUiState
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
        item {
            NoteCountWithListPreference(
                modifier = Modifier.fillMaxWidth(),
                noteCount = notes.size,
                selectLayout = selectLayout,
                isLinearLayout = layoutUiState.isLinearLayout,
                iconRes = layoutUiState.toggleIcon,
                contentDescriptionRes = layoutUiState.toggleContentDescription
            )
        }

        items(items = notes, key = { note -> note.id }) { note ->
            SwipeBox(
                swipeEnabled = swipeEnabled,
                onDelete = { onDeleteClick(note) },
                onEdit = { openNote(note.id) },
                modifier = Modifier
                    .animateItemPlacement()
            ) {
                NoteListCard(
                    modifier = Modifier
                        .animateItemPlacement(),
                    swipeEnabled = swipeEnabled,
                    title = note.title.removeExtraSpacesAndLines(),
                    body = note.body.removeExtraSpacesAndLines(),
                    dateTime = getFormattedTime(note.dateTime),
                    openNote = { openNote(note.id) },
                    onDeleteClick = { onDeleteClick(note) },
                    imageUri = note.imageUri,
                    onPickImage = { uri ->
                        onPickImage(note.id, uri)
                    }
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
    swipeEnabled: Boolean,
    title: String,
    body: String,
    dateTime: String,
    openNote: () -> Unit,
    onDeleteClick: () -> Unit,
    imageUri: String?,
    onPickImage: (Uri?) -> Unit
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
            LeadingNoteImage(
                swipeEnabled = swipeEnabled,
                imageUri = imageUri,
                onPickImage = onPickImage
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.medium_padding)))
            BodyContent(
                modifier = Modifier.weight(1f),
                spacing = dimensionResource(id = R.dimen.very_small_padding),
                maxBodyLines = 2,
                title = title,
                body = body,
                dateTime = dateTime
            )
//            DeleteIcon(onDeleteClick = onDeleteClick) // since swipe to dismiss is used
        }
    }
}

@Composable
private fun NoteGridCard(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    dateTime: String,

    openNote: () -> Unit
) {
    Card(
        modifier = modifier.wrapContentHeight(),
        onClick = openNote
    ) {
        BodyContent(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding)),
            spacing = dimensionResource(id = R.dimen.small_padding),
            maxBodyLines = 4,
            title = title,
            body = body,
            dateTime = dateTime
        )
    }
}

@Composable
fun LeadingNoteImage(
    modifier: Modifier = Modifier,
    swipeEnabled: Boolean,
    imageUri: String?,
    onPickImage: (Uri?) -> Unit
) {
    // not good practice, will modify later
    var pickedImageUri by remember { mutableStateOf(imageUri) }

    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            onPickImage(uri)
            pickedImageUri = uri.toString()
        })


    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.list_image_size))
            .let {
                if (swipeEnabled) it.clickable {
                    coroutineScope.launch {
                        pickImage.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                } else it
            },

        contentAlignment = Alignment.Center
    ) {
        if (pickedImageUri.isNullOrBlank()) {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.list_icon_size)),
                painter = painterResource(id = R.drawable.note),
                contentDescription = null,
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .matchParentSize()
                    .clip(MaterialTheme.shapes.small),
                model = Uri.parse(pickedImageUri),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
private fun BodyContent(
    modifier: Modifier = Modifier,
    maxBodyLines: Int = 1,
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

@Composable
fun NoteCountWithListPreference(
    modifier: Modifier = Modifier,
    noteCount: Int,
    selectLayout: (Boolean) -> Unit,
    isLinearLayout: Boolean,
    @DrawableRes iconRes: Int,
    @StringRes contentDescriptionRes: Int
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.lazy_item_note_count_padding)),
            text = pluralStringResource(id = R.plurals.notes, count = noteCount, noteCount),
            style = MaterialTheme.typography.bodyMedium
        )
        // Remove the ripple effect for the button

        IconToggleButton(
            modifier = Modifier.defaultMinSize(0.dp, 0.dp),
            checked = isLinearLayout,
            onCheckedChange = { selectLayout(it) },
            colors = IconButtonDefaults.iconToggleButtonColors(checkedContentColor = LocalContentColor.current)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = stringResource(contentDescriptionRes)
            )
        }
    }
}

// Use this in viewModel for SOC
fun String.removeExtraSpacesAndLines(): String {
    return trimIndent().replace("\\s+".toRegex(), " ")
}

/*
@Composable
fun DeleteIcon(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {
    IconButton(modifier = modifier, onClick = onDeleteClick) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete)
        )
    }
}

// Will not fit the requirement
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteOrTodoTab(
    modifier: Modifier = Modifier
) {
    var state by remember { mutableIntStateOf(0) }
    val titleAndIcon = listOf(
        R.string.notes to R.drawable.note,
        R.string.todos to R.drawable.check_list
    )

    PrimaryTabRow(modifier = modifier, selectedTabIndex = state) {
        titleAndIcon.forEachIndexed { index, (title, drawable) ->
            Tab(
                selected = state == index,
                onClick = { state = index },
                text = {
                    Text(
                        text = stringResource(id = title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = drawable),
                        contentDescription = null
                    )
                }
            )
        }
    }
}

                    if (showFAB) {
                        IconButton(
                            modifier = Modifier,
                            onClick = { showFAB = false }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = stringResource(R.string.search)
                            )
                        }
                    }
                    AnimatedVisibility(visible = !showFAB) {
                        NoteSearchBar(
                            query = noteListUiState.searchQuery,
                            onQueryChange = searchSViewModel::onSearchQueryChange,
                            notes = noteListUiState.notes,
                            openNote = navigateToEdit,
                            isActive = ::setFabState,
                            activeStatus = !showFAB
                        )
                    }
*/