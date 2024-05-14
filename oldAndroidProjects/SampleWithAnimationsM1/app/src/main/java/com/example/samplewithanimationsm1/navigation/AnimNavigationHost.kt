package com.example.samplewithanimationsm1.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.samplewithanimationsm1.core.presentation.components.listOfAnimNavBarItem
import com.example.samplewithanimationsm1.screens.HomeScreen
import com.example.samplewithanimationsm1.screens.MailScreen

@Composable
fun AnimNavigationHost(
    innerPadding: PaddingValues,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = listOfAnimNavBarItem[0].route
    ) {
        composable(listOfAnimNavBarItem[0].route) {
            HomeScreen(
                innerPadding = innerPadding,
                snackbarHostState = snackbarHostState
            )
        }
        composable(listOfAnimNavBarItem[1].route) {
            MailScreen(
                innerPadding = innerPadding,
                snackbarHostState = snackbarHostState
            )
        }
        composable(listOfAnimNavBarItem[2].route) {
            MailScreen(
                innerPadding = innerPadding,
                snackbarHostState = snackbarHostState
            )
        }
        composable(listOfAnimNavBarItem[3].route) {
            MailScreen(
                innerPadding = innerPadding,
                snackbarHostState = snackbarHostState
            )
        }
    }
}