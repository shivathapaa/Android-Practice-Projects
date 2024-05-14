package com.example.packagem3.ui.presentation.snackbar_scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


@Composable
fun MySnackbarExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
//        MySimpleSnackbar(modifier = modifier)
//        MyIndefiniteSnackbar(modifier = modifier)
        MyCoroutineSnackbar(modifier = modifier)
//        MyMultilineSnackbar(modifier = modifier)
    }
}

@Composable
fun MySimpleSnackbar(
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            var clickCount by remember { mutableIntStateOf(0) }
            ExtendedFloatingActionButton(onClick = {
                scope.launch { snackbarHostState.showSnackbar("Snackbar # ${++clickCount}") }
            }) {
                Text(text = "Show snackbar")
            }
        }
    ) { innerPadding ->
        Text(
            text = "Body content",
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

@Composable
fun MyIndefiniteSnackbar(
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            var clickCount by remember { mutableIntStateOf(0) }
            ExtendedFloatingActionButton(onClick = {
                // show snackbar as a suspend function
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Snackbar # ${++clickCount}",
                        actionLabel = "Action",
                        withDismissAction = true,
                        duration = SnackbarDuration.Indefinite
                    )
                }
            }) {
                Text(text = "Show snackbar")
            }
        }
    ) { innerPadding ->
        Text(
            text = "Body content",
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

@Composable
fun MyCoroutineSnackbar(
    modifier: Modifier = Modifier
) {
    // decouple snackbar host state from scaffold state for demo purposes
    // this state, channel and flow is for demo purposes to demonstrate business logic layer
    val snackbarHostState = remember { SnackbarHostState() }
    // we allow only one snackbar to be in the queue here, hence conflated
    val channel = remember { Channel<Int>(Channel.CONFLATED) }

    LaunchedEffect(key1 = channel) {
        channel.receiveAsFlow().collect { index ->
            val result = snackbarHostState.showSnackbar(
                message = "Snackbar # $index",
                actionLabel = "Action on $index"
            )

            when (result) {
                SnackbarResult.ActionPerformed -> {
                    // action has been performed
                }

                SnackbarResult.Dismissed -> {
                    // dismissed, no action needed
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            var clickCount by remember { mutableIntStateOf(0) }

            ExtendedFloatingActionButton(onClick = {
                // offset snckbar data to the business logic
                channel.trySend(++clickCount)
            }) {
                Text(text = "Show snackbar")
            }
        }
    ) { innerPadding ->
        Text(
            "Snackbar demo",
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize()
        )
    }

}

@Composable
fun MyMultilineSnackbar(
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar {
                    Text(
                        text = data.visuals.message,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                scope.launch {
                    val longMessage =
                        "This is a very long message which may overflow the snackbar area. This is just a test snackbar message for multi-line support. M3 recommends max 2 lines so be careful with the use."
                    snackbarHostState.showSnackbar(longMessage)
                }
            }) {
                Text("Show snackbar")
            }
        }
    ) { innerPadding ->
        Text(
            "Snackbar demo",
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize()
        )
    }

}