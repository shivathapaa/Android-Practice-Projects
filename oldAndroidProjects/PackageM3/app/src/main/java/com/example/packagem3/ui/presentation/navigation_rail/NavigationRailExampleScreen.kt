package com.example.packagem3.ui.presentation.navigation_rail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.packagem3.ui.presentation.navigation_bar.itemListOfMyNavigationBar

@Composable
fun NavigationRailExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        MyNavigationRailWithOnlySelectedLabels(modifier = Modifier)
    }
}

@Composable
fun MyNavigationRailWithOnlySelectedLabels(
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = itemListOfMyNavigationBar

    NavigationRail(
        modifier = modifier
    ) {
        items.take(5).forEachIndexed { index, item ->
            NavigationRailItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = { Icon(imageVector = item.filledIcon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                enabled = true,
                alwaysShowLabel = false
            )

        }
    }
}