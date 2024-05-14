package com.example.musicsample.navigation.nav_drawer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicsample.core.components.presentation.SampleModalNavigationDrawer
import com.example.musicsample.navigation.nav_drawer.settings.settingsNestedGraph
import com.example.musicsample.navigation_drawer.presentation.AboutScreen
import com.example.musicsample.navigation_drawer.presentation.EqualizerScreen
import com.example.musicsample.navigation_drawer.presentation.FoldersScreen
import com.example.musicsample.navigation_drawer.presentation.PlayingQueueScreen
import com.example.musicsample.navigation_drawer.presentation.ScanMediaScreen

@Composable
fun NavDrawerNavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = LibraryDestination.route,
        modifier = modifier
    ) {
//        addNavigationBarNestedGraph(navController, modifier = modifier)
        settingsNestedGraph(navController)

        composable(route = LibraryDestination.route) {
            SampleModalNavigationDrawer(navController = navController)
        }

        composable(route = PlayingQueueDestination.route) {
            PlayingQueueScreen()
        }

        composable(route = FoldersDestination.route) {
            FoldersScreen()
        }

        composable(route = EqualizerDestination.route) {
            EqualizerScreen()
        }

        composable(route = ScanMediaDestination.route) {
            ScanMediaScreen()
        }

//        composable(route = SettingsDestination.route) {
//            SettingsScreen()
//        }

        composable(route = AboutDestination.route) {
            AboutScreen()
        }
    }
}