package com.stapplications.notes.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOutExpo
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.stapplications.notes.R
import com.stapplications.notes.presentation.home.getFormattedTime
import com.stapplications.notes.presentation.screens.note.presentation.NoteListCard
import com.stapplications.notes.search.NoteS
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    openNote: (Int) -> Unit,
    onQueryChange: (String) -> Unit,
    notes: List<NoteS>,
    onActiveChange: (Boolean) -> Unit,
    onTodoClick: (Boolean) -> Unit,
    searchPlaceholder: String
) {
    var isActive by rememberSaveable { mutableStateOf(false) }

    var showTodo by rememberSaveable { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    var animationPlayed by rememberSaveable { mutableStateOf(false) }
    //Searchbar slide animation
    var isSearchBarVisible by remember { mutableStateOf(false) }
    var isInitialVisible by rememberSaveable { mutableStateOf(true) }
    var isSecondPhaseVisible by rememberSaveable { mutableStateOf(false) }

    // Execute animation only once when the app launches for the first time
    LaunchedEffect(animationPlayed) {
        delay(5)
        isSearchBarVisible = true

        if (!animationPlayed) {
            delay(1500)
            isInitialVisible = false

            delay(300)
            isSecondPhaseVisible = true

            animationPlayed = true
        }
    }

    AnimatedVisibility(
        visible = isSearchBarVisible,
        enter = fadeIn() + slideInVertically()
    ) {
        SearchBar(
            modifier = modifier,
            query = query,
            onQueryChange = { onQueryChange(it) },
            onSearch = {
                coroutineScope.launch { keyboardController?.hide() }
                // perform search action here
            },
            active = isActive,
            onActiveChange = {
                isActive = it
                if (!it) {
                    onQueryChange("")
                }
            },
            placeholder = {
                AnimatedVisibility(
                    visible = isInitialVisible,
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = EaseInOutExpo
                        )
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.note),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
                        Text("Notes")
                    }
                }

                AnimatedVisibility(
                    visible = isSecondPhaseVisible,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = EaseInOutExpo
                        )
                    )
                ) {
                    Text(searchPlaceholder)
                }
            },
            leadingIcon = {
                if (isActive) {
                    IconButton(onClick = {
                        onQueryChange("")
                        isActive = false
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.go_back)
                        )
                    }
                } else {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                }
            },
            trailingIcon = {
                var closeDescription = stringResource(R.string.close)
                if (isActive) {
                    IconButton(onClick = {
                        if (query.isNotEmpty()) {
                            onQueryChange("")
                            closeDescription = "Clear"
                        } else {
                            isActive = false
                            closeDescription = "Close"
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = closeDescription
                        )
                    }
                } else {
                    IconButton(onClick = {
                        showTodo = !showTodo
                        onTodoClick(showTodo)
                    }) {
                        if (showTodo) {
                            Icon(
                                painter = painterResource(id = R.drawable.note),
                                contentDescription = stringResource(id = R.string.notes)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.check_list),
                                contentDescription = stringResource(id = R.string.todos)
                            )
                        }
                    }
                }
            }
        ) {
            MultiSelectionChipsForSearchFilter()
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(
                        bottom = dimensionResource(id = R.dimen.small_padding),
                        start = dimensionResource(id = R.dimen.small_padding),
                        end = dimensionResource(id = R.dimen.small_padding)
                    )
                    .animateContentSize(),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding)),
            ) {
                items(items = notes, key = { note -> note.id }) { note ->
                    NoteListCard(
                        swipeEnabled = false,
                        title = note.title,
                        body = note.body,
                        dateTime = getFormattedTime(note.dateTime),
                        openNote = { openNote(note.id.toInt()) },
                        onDeleteClick = { },
                        imageUri = null,
                        onPickImage = { uri -> }
                    )
                }
            }
        }
    }
}