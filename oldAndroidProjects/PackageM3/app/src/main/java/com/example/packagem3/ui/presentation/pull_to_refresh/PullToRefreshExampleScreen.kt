package com.example.packagem3.ui.presentation.pull_to_refresh

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import kotlinx.coroutines.delay

@Composable
fun PullToRefreshExampleScreen(
    modifier: Modifier = Modifier
) {
    MyPullToRefreshWithScaling(modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySimplePullToRefresh(
    modifier: Modifier = Modifier
) {
    var itemCount by remember { mutableIntStateOf(15) }
    val state = rememberPullToRefreshState()

    if (state.isRefreshing) {
        LaunchedEffect(true) {
            // fetch data
            delay(1500)
            itemCount += 5
            state.endRefresh()
        }
    }

    Box(modifier = modifier.nestedScroll(state.nestedScrollConnection)) {
        LazyColumn(Modifier.fillMaxSize()) {
            if (!state.isRefreshing) {
                items(itemCount) {
                    ListItem(headlineContent = { Text(text = "Item Count ${itemCount - it}") })
                }
            }
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = state
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPullToRefreshWithScaling(
    modifier: Modifier = Modifier
) {
    var itemCount by remember { mutableIntStateOf(15) }
    val state = rememberPullToRefreshState()

    if (state.isRefreshing) {
        LaunchedEffect(true) {
            // fetch data
            delay(1500)
            itemCount += 5
            state.endRefresh()
        }
    }

    val scaleFraction =
        if (state.isRefreshing) 1f else LinearOutSlowInEasing.transform(state.progress)
            .coerceIn(0f, 1f)

    Box(modifier = modifier.nestedScroll(state.nestedScrollConnection)) {
        LazyColumn(Modifier.fillMaxSize()) {
            if (!state.isRefreshing) {
                items(itemCount) {
                    ListItem(headlineContent = { Text(text = "Item Count ${itemCount - it}") })
                }
            }
        }
        PullToRefreshContainer(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .graphicsLayer(scaleX = scaleFraction, scaleY = scaleFraction),
            state = state
        )
    }

}