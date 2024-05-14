package com.example.samplewithanimationsm1.core.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AnimNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        listOfAnimNavBarItem.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(text = screen.label) },
                icon = {
                    if (selected) {
                        Icon(imageVector = screen.filledIcon, contentDescription = null)
                    } else {
                        Icon(imageVector = screen.outlinedIcon, contentDescription = null)
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}

data class AnimNavBarItem(
    val label: String,
    val route: String,
    val onClick: () -> Unit = {},
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector
)

val listOfAnimNavBarItem = listOf(
    AnimNavBarItem(
        label = "Home",
        route = "home",
        onClick = {},
        filledIcon = Icons.Filled.Home,
        outlinedIcon = Icons.Outlined.Home
    ),
    AnimNavBarItem(
        label = "Mail",
        route = "mail",
        onClick = {},
        filledIcon = Icons.Filled.Email,
        outlinedIcon = Icons.Outlined.Email
    ),
    AnimNavBarItem(
        label = "Notifications",
        route = "notifications",
        onClick = {},
        filledIcon = Icons.Filled.Notifications,
        outlinedIcon = Icons.Outlined.Notifications
    ),
    AnimNavBarItem(
        label = "Profile",
        route = "profile",
        onClick = {},
        filledIcon = Icons.Filled.Person,
        outlinedIcon = Icons.Outlined.Person
    )
)