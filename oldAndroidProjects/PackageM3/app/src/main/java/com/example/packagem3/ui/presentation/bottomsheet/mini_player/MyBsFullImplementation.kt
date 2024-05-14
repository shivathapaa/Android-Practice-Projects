package com.example.packagem3.ui.presentation.bottomsheet.mini_player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.packagem3.ui.presentation.navigation_bar.MyNavigationBarWithOnlySelectedLabels
import com.example.packagem3.ui.presentation.top_app_bar.components.FakeListItemsWithState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBsFullImplementation(
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    val scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()

    val scope = rememberCoroutineScope()


    Scaffold(
//        bottomBar = { MyNavigationBarWithOnlySelectedLabels() }
    ) { innerPadding ->
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetContent = {
                MyExpandedPlayerSampleScreen()
//            MyNavigationBarWithOnlySelectedLabelsAndReordering()
            },
            sheetPeekHeight = 172.dp,
            sheetDragHandle = null,
            sheetSwipeEnabled = true,
//            topBar = {
//                ExitUntilCollapsedLargeTopAppBar(
//                    scrollBehavior = scrollBehavior
//                )
//            },
            modifier = modifier
                .padding(innerPadding)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        ) { valuePadding ->

            Column {
                Button(onClick = {
                    scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                }) {
                    Text(text = "expand")
                }
                FakeListItemsWithState(
                    paddingValues = valuePadding,
                    lazyListState = listState,
                    modifier = Modifier
                )
            }
        }
    }
}
