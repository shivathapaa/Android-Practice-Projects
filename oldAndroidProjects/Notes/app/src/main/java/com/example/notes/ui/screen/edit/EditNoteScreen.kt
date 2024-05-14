package com.example.notes.ui.screen.edit

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.R
import com.example.notes.navigation.NavigationDestination
import com.example.notes.ui.NoteAppViewModelProvider
import com.example.notes.ui.common.NoteFloatingActionButton
import com.example.notes.ui.common.NoteTopAppBar
import com.example.notes.ui.screen.details.NoteEditor
import kotlinx.coroutines.launch

object EditNoteDestination : NavigationDestination {
    override val route: String = "edit_notes"
    override val titleRes: Int = R.string.edit
    const val noteIdArg = "noteId"
    val routeWithArgs = "${EditNoteDestination.route}/{$noteIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(
    onNavigateUp: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: EditNoteViewModel = viewModel(factory = NoteAppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(id = EditNoteDestination.titleRes),
                canNavigateBack = canNavigateBack,
                onNavigateUp = onNavigateUp
            )
        },
        floatingActionButton = {
            NoteFloatingActionButton(
                onClick = {
                      coroutineScope.launch {
                          viewModel.saveUpdate()
                          navigateBack()
                      }
                },
                icon = painterResource(id = R.drawable.save),
                text = stringResource(id = R.string.save),
                functionDescription = stringResource(id = R.string.save)
            )
        },
        modifier = modifier
    ) { innerPadding ->
        NoteEditor(
            notesUiState = viewModel.notesUiState,
            onValueChange = viewModel::updateUiState,
            bodyTextCount = viewModel.bodyCharacterCount,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

