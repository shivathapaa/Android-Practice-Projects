package com.example.samplewithanimationsm1.core.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun AnimNavDrawer(
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = { DrawerContents(drawerState = drawerState) }
    ) {
        AnimScaffold(openDrawer = { coroutineScope.launch { drawerState.open() } })
    }
}

@Composable
fun DrawerContents(
    drawerState: DrawerState,
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    val coroutineScope = rememberCoroutineScope()

    ModalDrawerSheet(
        modifier = modifier,
        windowInsets = WindowInsets(top = 0)
    ) {
        Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))

        listOfAnimNavBarItem.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = { Text(text = item.label) },
                icon = { Icon(imageVector = item.filledIcon, contentDescription = null) },
                selected = selectedItem == index,
                onClick = {
                    coroutineScope.launch { drawerState.close() }
                    selectedItem = index
                }
            )
        }
    }
}