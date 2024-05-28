package com.shivathapaa.praticepath.presentation.home


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.shivathapaa.praticepath.R
import com.shivathapaa.praticepath.presentation.components.AddSaveFloatingActionButton
import com.shivathapaa.praticepath.presentation.components.isScrollingUp
import com.shivathapaa.praticepath.presentation.home.components.CustomTopAppBar
import com.shivathapaa.praticepath.presentation.home.components.NoteSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToAddNote: () -> Unit,
    navigateToEdit: (Int) -> Unit,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {

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

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                scrollBehavior = scrollBehavior
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    NoteSearchBar(
                        onMenuClick = { /*TODO*/ },
                        onProfileClick = { /*Todo*/}
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            AnimatedVisibility(visible = showFAB) {
                AddSaveFloatingActionButton(
                    onClick = { navigateToAddNote() },
                    text = stringResource(R.string.add_note),
                    icon = Icons.Default.Add,
                    extended = listState.isScrollingUp()
/*                    if (layoutUiState.isLinearLayout) {
                        gridState.isScrollingUp()
                    } else {
                        listState.isScrollingUp()
                    }*/
                )
            }
        }
    ) { innerPadding ->
        NotesListScreen(
            modifier = Modifier,
            innerPadding = innerPadding,
            navigateToEdit = {},
            onDeleteClick = {},
            notes = emptyList()
        )
    }
}
