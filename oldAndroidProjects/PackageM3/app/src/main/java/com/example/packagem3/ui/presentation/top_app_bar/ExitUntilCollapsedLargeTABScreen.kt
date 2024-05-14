package com.example.packagem3.ui.presentation.top_app_bar

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.packagem3.ui.presentation.top_app_bar.components.ExitUntilCollapsedLargeTopAppBar
import com.example.packagem3.ui.presentation.top_app_bar.components.FakeListItemsWithState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExitUntilCollapsedLargeTABScreen(
    modifier: Modifier = Modifier
) {
    val scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    val listState = rememberLazyListState()
    val expandedFab by remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ExitUntilCollapsedLargeTopAppBar(
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            MyFloatingActionButton(
                expanded = expandedFab
            )
        }
    ) { innerPadding ->
        FakeListItemsWithState(
            paddingValues = innerPadding,
            lazyListState = listState,
            modifier = Modifier
        )
    }
}