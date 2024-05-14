package com.stapplications.notes.presentation.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stapplications.notes.NoteViewModelProvider
import com.stapplications.notes.R
import com.stapplications.notes.presentation.components.AddSaveFloatingActionButton
import com.stapplications.notes.presentation.components.isScrollingUp
import com.stapplications.notes.presentation.home.components.CustomTopAppBar
import com.stapplications.notes.presentation.screens.note.presentation.NoteScreen
import com.stapplications.notes.presentation.screens.todo.presentation.TodoScreen
import com.stapplications.notes.presentation.search.NoteSearchBar
import com.stapplications.notes.search.SearchSViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToAddNote: () -> Unit,
    navigateToEdit: (Int) -> Unit,
    snackbarHostState: SnackbarHostState,
    viewModel: HomeScreenViewModel = viewModel(factory = NoteViewModelProvider.Factory),
    searchSViewModel: SearchSViewModel = viewModel(factory = NoteViewModelProvider.Factory)
) {
    val layoutUiState by viewModel.layoutUiState.collectAsState()
    val homeUiState by viewModel.homeUiState.collectAsState()
    val noteListUiState = searchSViewModel.noteListState

    var showFAB by remember { mutableStateOf(true) }
    var showTodo by rememberSaveable { mutableStateOf(false) }

    fun setFabState(newState: Boolean) {
        showFAB = !newState
    }

    val listState = rememberLazyListState()
    val gridState = rememberLazyStaggeredGridState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val fabIcon = if (showTodo) R.drawable.check_list else R.drawable.add_note
    val fabText = if (showTodo) R.string.add_todo else R.string.add_note

    // add list to the document for search
    LaunchedEffect(homeUiState.noteList) { viewModel.addNoteToDocument(homeUiState.noteList) }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                scrollBehavior = scrollBehavior
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    NoteSearchBar(
                        query = noteListUiState.searchQuery,
                        onQueryChange = searchSViewModel::onSearchQueryChange,
                        notes = noteListUiState.notes,
                        openNote = navigateToEdit,
                        onActiveChange = ::setFabState,
                        onTodoClick = { showTodo = it },
                        searchPlaceholder = if (showTodo) stringResource(R.string.search_your_todos)
                        else stringResource(R.string.search_your_notes)
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            AnimatedVisibility(visible = showFAB) {
                AddSaveFloatingActionButton(
                    onClick = {
                        if (showTodo) {
                            /*Todo*/
                        } else {
                            navigateToAddNote()
                        }
                    },
                    text = stringResource(fabText),
                    icon = fabIcon,
                    extended = if (layoutUiState.isLinearLayout) {
                        gridState.isScrollingUp()
                    } else {
                        listState.isScrollingUp()
                    }
                )
            }
        }
    ) { innerPadding ->
        AnimatedContent(targetState = showTodo, label = "TodoNoteSwitch") { isTodoVisible ->
            when (isTodoVisible) {
                true -> TodoScreen(
                    modifier = Modifier,
                    innerPadding = innerPadding
                )

                false ->
                    NoteScreen(
                        innerPadding = innerPadding,
                        notes = homeUiState.noteList,
                        gridState = gridState,
                        listState = listState,
                        layoutUiState = layoutUiState,
                        onSelectLayout = viewModel::selectLayout,
                        navigateToEdit = navigateToEdit,
                        onDeleteClick = { note -> coroutineScope.launch { viewModel.deleteNote(note) } },
                        onPickImage = { noteId, uri ->
                            coroutineScope.launch {
                                viewModel.addImageUri(
                                    context = context,
                                    imageUri = uri,
                                    noteId = noteId
                                )
                            }
                        }
                    )
            }

        }
    }
}
