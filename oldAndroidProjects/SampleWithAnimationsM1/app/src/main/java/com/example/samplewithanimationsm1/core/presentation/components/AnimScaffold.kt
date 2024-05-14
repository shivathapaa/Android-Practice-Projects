package com.example.samplewithanimationsm1.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.rememberNavController
import com.example.samplewithanimationsm1.core.presentation.components.searchbox.AnimSearchBox
import com.example.samplewithanimationsm1.navigation.AnimNavigationHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimScaffold(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val navController = rememberNavController()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomFlexibleTopAppBar(scrollBehavior = scrollBehavior) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) { AnimSearchBox(openDrawer = openDrawer) }
            }
        },
        bottomBar = { AnimNavigationBar(navController) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        AnimNavigationHost(
            navController = navController,
            snackbarHostState = snackbarHostState,
            innerPadding = innerPadding
        )
    }
}

/*
Will not be used as I am using search bar.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimTopBar(
    modifier: Modifier = Modifier,
    title: String = "Home"
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = { Text(text = title) }
    )
}
*/