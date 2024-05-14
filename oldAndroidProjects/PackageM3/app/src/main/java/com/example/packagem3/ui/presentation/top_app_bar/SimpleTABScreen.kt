package com.example.packagem3.ui.presentation.top_app_bar

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.packagem3.R
import com.example.packagem3.ui.presentation.top_app_bar.components.FakeListItemsWithState
import com.example.packagem3.ui.presentation.top_app_bar.components.SimpleEnterAlwaysCenteredTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTABScreen(
    modifier: Modifier = Modifier
) {
    val scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior()

    val listState = rememberLazyListState()
    val expandedFab by remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleEnterAlwaysCenteredTopBar(
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

@Composable
fun MyFloatingActionButton(
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    ExtendedFloatingActionButton(
        text = {
            Text(text = stringResource(R.string.edit))
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = null
            )
        },
        expanded = expanded,
        onClick = { },
        modifier = modifier
    )
}
