package com.stapplications.notes.presentation.screens.note.presentation.update.edit

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stapplications.notes.NoteViewModelProvider
import com.stapplications.notes.R
import com.stapplications.notes.presentation.components.AddSaveFloatingActionButton
import com.stapplications.notes.presentation.components.isScrollingUp
import com.stapplications.notes.presentation.screens.note.presentation.update.components.NoteUpsertTopAppBar
import com.stapplications.notes.presentation.screens.note.presentation.update.components.NoteUpsertBodyContainer
import com.stapplications.notes.presentation.screens.note.presentation.update.components.shareNote
import kotlinx.coroutines.launch

@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    navigateBack: () -> Unit,
    snackbarHostState: SnackbarHostState,
    viewModel: EditNoteViewModel = viewModel(factory = NoteViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val keyboard = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = {
            NoteUpsertTopAppBar(
                onUpClick = {
                    coroutineScope.launch {
                        keyboard?.hide()
                        navigateUp()
                        viewModel.saveNote()
                        snackbarHostState.showSnackbar("Note has been successfully saved.")
                    }
                },
                onCancelClick = {
                    keyboard?.hide()
                    navigateBack()
                },
                onDeleteClick = {
                    coroutineScope.launch {
                        viewModel.deleteNote()
                        navigateBack()
                        snackbarHostState.showSnackbar("Note has been deleted successfully.")
                    }
                },
                onShareClick = {
                    keyboard?.hide()
                    shareNote(
                        title = viewModel.noteUiState.noteDetails.title,
                        body = viewModel.noteUiState.noteDetails.body,
                        date = if ((viewModel.noteUiState.noteDetails.dateTime).isNotBlank()) viewModel.noteUiState.noteDetails.dateTime
                        else viewModel.getFormattedTime(),
                        context = context
                    )
                },
                showDeleteIcon = true
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            AddSaveFloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        keyboard?.hide()
//                        navigateBack()
                        viewModel.saveNote()
                        snackbarHostState.showSnackbar("Note has been successfully saved.")
                    }
                },
                text = stringResource(R.string.save),
                icon = R.drawable.save,
                extended = listState.isScrollingUp()
            )
        }
    ) { innerPadding ->
        NoteUpsertBodyContainer(
            modifier = modifier,
            noteUiState = viewModel.noteUiState,
            onValueChange = viewModel::updateUiState,
            bodyTextCount = viewModel.bodyWordCount,
            timeAndDate = viewModel.getFormattedTime(),
            listState = listState,
            innerPadding = innerPadding
        )
    }
}