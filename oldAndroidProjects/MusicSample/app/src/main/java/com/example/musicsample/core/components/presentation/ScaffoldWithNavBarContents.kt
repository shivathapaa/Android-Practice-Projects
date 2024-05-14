package com.example.musicsample.core.components.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.musicsample.R
import com.example.musicsample.navigation.nav_bar.NavBarNavigationHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScaffoldWithNavBarContents(
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val coroutineScope = rememberCoroutineScope()
//    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }

    var openBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                MusicSampleSearchBar(
                    onMenuClick = { coroutineScope.launch { drawerState.open() } },
                    modifier = Modifier
                )
            }
        },
        bottomBar = { NavigationBarWithDivider(navController = navController) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        NavBarNavigationHost(
            snackbarHostState = snackbarHostState,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )

        if (openBottomSheet) {
            MusicSampleBottomSheet(openBottomSheet = { openBottomSheet = it })
        }
    }
}

//        NavDrawerNavigationHost(
//            navController = navController,
//            modifier = Modifier.padding(innerPadding)
//        )

//        scaffoldContent(Modifier.padding(innerPadding))
//        HomeScreen(modifier = Modifier.padding(innerPadding))
//        FakeContentForScaffold(
//            innerPadding = innerPadding,
//            listState = listState,
//            coroutineScope = coroutineScope,
//            snackbarHostState = snackbarHostState,
//            openBottomSheet = { openBottomSheet = it }
//        )


@Composable
fun FakeContentForScaffold(
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
