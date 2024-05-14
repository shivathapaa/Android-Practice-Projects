package com.example.musicsample.core.components.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.musicsample.navigation.nav_bar.listOfNavBarDestination

@Composable
fun NavigationBarWithDivider(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        HorizontalDivider(
            thickness = Dp.Hairline,
            color = MaterialTheme.colorScheme.secondaryContainer
        )
        SampleNavigationBar(navController = navController)
    }
}

@Composable
fun SampleNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigationBarScreenDestinations = listOfNavBarDestination

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = modifier
    ) {
        navigationBarScreenDestinations.take(5).forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // pop up to the start destination of the graph to avoid building up a large stack of destinations on the back stack as users select items
//                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        popUpTo(0) { saveState = true }
                        // avoid multiple copies of the same destination when re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                        Icon(imageVector = screen.outlinedIcon, contentDescription = null)
                    } else {
                        Icon(imageVector = screen.filledIcon, contentDescription = null)
                    }
                },
                label = {
                    Text(
                        text = stringResource(id = screen.titleRes),
                        fontSize = MaterialTheme.typography.labelLarge.fontSize
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}
