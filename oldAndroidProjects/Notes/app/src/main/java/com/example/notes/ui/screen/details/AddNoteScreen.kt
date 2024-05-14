package com.example.notes.ui.screen.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.R
import com.example.notes.data.NoteDetails
import com.example.notes.data.NotesUiState
import com.example.notes.navigation.NavigationDestination
import com.example.notes.ui.NoteAppViewModelProvider
import com.example.notes.ui.common.NoteFloatingActionButton
import com.example.notes.ui.common.NoteTopAppBar
import kotlinx.coroutines.launch

object AddNoteDestination : NavigationDestination {
    override val route: String = "note_details"
    override val titleRes: Int = R.string.add_note
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    navigateUp: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    addNoteViewModel: AddNoteViewModel = viewModel(factory = NoteAppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(AddNoteDestination.titleRes),
                canNavigateBack = canNavigateBack,
                onNavigateUp = navigateUp
            )
        },
        floatingActionButton = {
            NoteFloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                          addNoteViewModel.saveNote()
                          navigateBack()
                      }
                },
                expanded = true,
                icon = painterResource(id = R.drawable.save),
                text = stringResource(id = R.string.save),
                functionDescription = stringResource(id = R.string.save)
            )
        }
    ) { innerPadding ->
        NoteEditor(
            notesUiState = addNoteViewModel.notesUiState,
            onValueChange = addNoteViewModel::updateUiState,
            bodyTextCount = addNoteViewModel.bodyWordCount,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun NoteEditor(
    notesUiState: NotesUiState,
    onValueChange: (NoteDetails) -> Unit,
    bodyTextCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
//            .verticalScroll(state = rememberScrollState())
            .fillMaxSize()

    ) {
        NoteTitleEdit(
            onTitleUpdate = { onValueChange(notesUiState.noteDetails.copy(title = it)) },
            title = notesUiState.noteDetails.title,
            modifier = Modifier.fillMaxWidth()
        )
        NoteBodyCharacterCount(
            characters = bodyTextCount,
            dateAndTime = getFormattedTime(),
            modifier = Modifier.fillMaxWidth()
        )
        NoteSubTitleEdit(
            onSubTitleUpdate = { onValueChange(notesUiState.noteDetails.copy(subTitle = it)) },
            subTitle = notesUiState.noteDetails.subTitle,
            modifier = Modifier
                .fillMaxWidth()
        )
        NoteBodyEdit(
            onBodyUpdate = { onValueChange(notesUiState.noteDetails.copy(body = it)) },
            body = notesUiState.noteDetails.body,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun NoteTitleEdit(
    onTitleUpdate: (String) -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
) {
    TextField(
        value = title,
        onValueChange = { onTitleUpdate(it) },
        maxLines = maxLines,
        textStyle = TextStyle(
            fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.alpha(0.7f)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent, // Hide focused state indicator
            unfocusedIndicatorColor = Color.Transparent, // Hide unfocused state indicator
            disabledIndicatorColor = Color.Transparent, // Hide disabled state indicator,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        shape = RectangleShape,
        modifier = modifier
    )
}

@Composable
fun NoteSubTitleEdit(
    onSubTitleUpdate: (String) -> Unit,
    subTitle: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 2,
) {
    TextField(
        value = subTitle,
        onValueChange = { onSubTitleUpdate(it) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.subtitle),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.alpha(0.6f)
            )
        },
        maxLines = maxLines,
        textStyle = TextStyle(
            fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent, // Hide focused state indicator
            unfocusedIndicatorColor = Color.Transparent, // Hide unfocused state indicator
            disabledIndicatorColor = Color.Transparent, // Hide disabled state indicator,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        shape = RectangleShape,
        modifier = modifier
    )
}

@Composable
fun NoteBodyCharacterCount(
    characters: Int,
    dateAndTime: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.medium_padding))
    ) {
        Text(
            text = "$dateAndTime  |  " + pluralStringResource(
                id = R.plurals.character_count,
                count = characters,
                characters
            ),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.alpha(0.6f)
        )
    }
}

@Composable
fun NoteBodyEdit(
    onBodyUpdate: (String) -> Unit,
    body: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = body,
        onValueChange = { onBodyUpdate(it) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.start_typing),
                modifier = Modifier.alpha(0.7f)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent, // Hide focused state indicator
            unfocusedIndicatorColor = Color.Transparent, // Hide unfocused state indicator
            disabledIndicatorColor = Color.Transparent, // Hide disabled state indicator,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        shape = RectangleShape,
        modifier = modifier
    )
}
