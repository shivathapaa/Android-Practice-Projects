package com.shivathapaa.praticepath.presentation.update.add

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.shivathapaa.praticepath.R
import com.shivathapaa.praticepath.presentation.components.AddSaveFloatingActionButton
import com.shivathapaa.praticepath.presentation.components.isScrollingUp
import com.shivathapaa.praticepath.presentation.update.components.NoteUpsertBodyContainer
import com.shivathapaa.praticepath.presentation.update.components.shareNote
import com.stapplications.notes.presentation.screens.note.presentation.update.components.NoteUpsertTopAppBar
import kotlinx.coroutines.launch

@Composable
fun AddNoteScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    navigateBack: () -> Unit,
    snackbarHostState: SnackbarHostState,
//    viewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val context = LocalContext.current

    val keyboard = LocalSoftwareKeyboardController.current

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
                onShareClick = {
                    keyboard?.hide()
                    shareNote(
                        title = viewModel.noteUiState.noteDetails.title,
                        body = viewModel.noteUiState.noteDetails.body,
                        date = viewModel.noteUiState.noteDetails.dateTime,
                        context = context
                    )
                },
                showDeleteIcon = false
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            AddSaveFloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        keyboard?.hide()
                        navigateBack()
                        viewModel.saveNote()
                        snackbarHostState.showSnackbar("Note has been successfully saved.")
                    }
                },
                text = stringResource(R.string.save),
                icon = Icons.Default.Check,
                extended = listState.isScrollingUp()
            )
        }
    ) { innerPadding ->
        NoteUpsertBodyContainer(
            noteUiState = viewModel.noteUiState,
            onValueChange = viewModel::updateUiState,
            bodyTextCount = viewModel.bodyWordCount,
            innerPadding = innerPadding,
            timeAndDate = viewModel.getFormattedTime(),
            listState = listState,
            modifier = Modifier
        )
    }
}
