package com.example.notes.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.R
import com.example.notes.data.Note
import com.example.notes.navigation.NavigationDestination
import com.example.notes.ui.NoteAppViewModelProvider
import com.example.notes.ui.common.NoteFloatingActionButton
import com.example.notes.ui.common.NoteTopAppBar
import com.example.notes.ui.theme.NotesTheme

object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.notes
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToAddNotes: () -> Unit,
    navigateToNoteEdit: (Int) -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = false,
    viewModel: HomeScreenViewModel = viewModel(factory = NoteAppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(id = HomeDestination.titleRes),
                canNavigateBack = canNavigateBack,
                scrollBehaviour = scrollBehavior
            )
        },
        floatingActionButton = {
            NoteFloatingActionButton(
                onClick = navigateToAddNotes,
                icon = painterResource(id = R.drawable.add_note),
                text = stringResource(id = R.string.add_note),
                functionDescription = stringResource(id = R.string.add_note)
            )
        },
        modifier = modifier
    ) { innerPadding ->
        HomeBody(
            noteList = homeUiState.noteList,
            onNoteClick = navigateToNoteEdit,
            onDeleteClick = { note -> viewModel.deleteNote(note) },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun HomeBody(
    noteList: List<Note>,
    onDeleteClick: (Note) -> Unit,
    onNoteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    NoteLazyList(
        onDeleteClick = onDeleteClick,
        onNoteClick = { onNoteClick(it.id) },
        notes = noteList,
        modifier = modifier
    )

    // small delay caused no note found to be shown TODO: Fix that and use it

//    if (noteList.isEmpty()) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = modifier
//        ) {
//            Text(
//                text = stringResource(R.string.no_notes_found),
//                textAlign = TextAlign.Center,
//                style = MaterialTheme.typography.titleLarge
//            )
//        }
//    } else {
//        NoteLazyList(
//            onDeleteClick = {  },
//            onNoteClick = { onNoteClick(it.id) },
//            notes = noteList,
//            modifier = modifier
//        )
//    }

}

@Composable
fun NoteLazyList(
    onDeleteClick: (Note) -> Unit,
    onNoteClick: (Note) -> Unit,
    notes: List<Note>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            HeaderTotalRecordDetails(
                totalNotes = notes.size.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.medium_padding))
                    .padding(
                        top = dimensionResource(id = R.dimen.medium_padding),
                        bottom = dimensionResource(
                            id = R.dimen.extra_small_padding
                        )
                    )
            )
        }

        items(items = notes, key = { note -> note.id }) { note ->
            NoteCard(
                headlineText = note.title,
                supportingText = note.body,
                onDeleteClick = { onDeleteClick(note) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onNoteClick(note) })
            )
        }
    }
}

@Composable
fun NoteCard(
    headlineText: String,
    supportingText: String,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: Int = R.drawable.note,
) {
    Card(
        modifier = modifier
    ) {
        ListItem(
            headlineContent = {
                var headText = headlineText
                if (headlineText.isEmpty()) {
                    headText = supportingText
                }
                HeadlineText(headlineText = headText)
            },
            supportingContent = { SupportingText(supportingText = supportingText) },
            leadingContent = { LeadingContentIcon(leadingIcon = leadingIcon) },
            trailingContent = { ListTrailingIcon(onDelete = onDeleteClick) },
            modifier = Modifier
        )
    }
}

@Composable
fun HeaderTotalRecordDetails(
    totalNotes: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.total_notes, totalNotes),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun LeadingContentIcon(
    leadingIcon: Int,
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = leadingIcon),
        contentDescription = null,
        modifier = modifier.rotate(270f)
    )
}

@Composable
fun ListTrailingIcon(
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { onDelete() },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.edit)
        )
    }
}

@Composable
fun HeadlineText(
    headlineText: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = headlineText,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
fun SupportingText(
    supportingText: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = supportingText,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Preview
@Composable
fun IconPreview() {
    NotesTheme {
        LeadingContentIcon(leadingIcon = R.drawable.note)
    }
}