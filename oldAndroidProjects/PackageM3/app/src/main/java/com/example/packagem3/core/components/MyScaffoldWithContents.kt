package com.example.packagem3.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.packagem3.R
import com.example.packagem3.feature.home.MyHomeScreenContents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MyScaffoldWithContents(
    drawerState: DrawerState,
//    scaffoldContent: @Composable (Modifier) -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }

    var openBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                MySearchBar(
                    onMenuClick = { coroutineScope.launch { drawerState.open() } },
                    modifier = Modifier
                )
            }
        },
        bottomBar = { MyNavigationBarFormBottom() },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

//        scaffoldContent(Modifier.padding(innerPadding))
        MyHomeScreenContents(modifier = Modifier.padding(innerPadding))
//        MyFakeContentForScaffold(
//            innerPadding = innerPadding,
//            listState = listState,
//            coroutineScope = coroutineScope,
//            snackbarHostState = snackbarHostState,
//            openBottomSheet = { openBottomSheet = it }
//        )

        if (openBottomSheet) {
            MyBottomSheet(openBottomSheet = { openBottomSheet = it })
        }
    }
}

@Composable
fun MyFakeContentForScaffold(
    innerPadding: PaddingValues,
    listState: LazyListState,
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    openBottomSheet: (Boolean) -> Unit,

    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = innerPadding,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_padding)),
        modifier = modifier
    ) {
        val list = List(100) { "Let's count $it" }

        item {
            FilledTonalButton(
                onClick = { openBottomSheet(true) },
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(
                            id = R.dimen.medium_padding
                        )
                    )
            ) {
                Text(text = "Open Bottomsheet")
            }
        }

        items(count = list.size) {
            Text(
                list[it],
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.medium_padding))
                    .clickable(onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Clicked on item $it",
                                actionLabel = "Navigate",
                                withDismissAction = true,
                                duration = SnackbarDuration.Short
                            )
                        }
                    })
            )
        }
    }
}

