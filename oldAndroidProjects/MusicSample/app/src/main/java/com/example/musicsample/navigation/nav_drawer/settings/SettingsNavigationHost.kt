package com.example.musicsample.navigation.nav_drawer.settings

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.musicsample.feature.settings.SettingsScreenContents
import com.example.musicsample.feature.settings.library.LibrarySettingScreen
import com.example.musicsample.feature.settings.now_playing.NowPlayingQueueListScreen
import com.example.musicsample.feature.settings.themes_and_colors.SettingThemeAndColorScreen
import com.example.musicsample.navigation.nav_drawer.SettingsDestination

/*
@Composable
fun SettingsContentNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = SettingsDestination.route,
        modifier = modifier
    ) {

        composable(SettingsDestination.route) {
            SettingsScreenContents(
                onLibraryClick = { navController.navigate(SettingsDestinations.Library.route) },
                onNowPlayingClick = { navController.navigate(SettingsDestinations.NowPlaying.route) },
                onThemesAndColorClick = { navController.navigate(SettingsDestinations.ThemesAndColor.route) },
            )
        }

        composable(SettingsDestinations.NowPlaying.route) {
            NowPlayingQueueListScreen()
        }

        composable(SettingsDestinations.Library.route) {
            LibrarySettingScreen()
        }

        composable(SettingsDestinations.ThemesAndColor.route) {
            SettingThemeAndColorScreen()
        }
    }
}
*/

fun NavGraphBuilder.settingsNestedGraph(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    navigation(
        startDestination = SettingsDestination.route,
        route = "nested_setting"
    ) {
        composable(SettingsDestination.route) {
            SettingsScreenContents(
                onLibraryClick = { navController.navigate(SettingsDestinations.Library.route) },
                onNowPlayingClick = { navController.navigate(SettingsDestinations.NowPlaying.route) },
                onThemesAndColorClick = { navController.navigate(SettingsDestinations.ThemesAndColor.route) },
                onNavigateUp = { navController.navigateUp() },
                modifier = modifier
            )
        }

        composable(SettingsDestinations.NowPlaying.route) {
            NowPlayingQueueListScreen(
                onNavigateUp = { navController.navigateUp() },
                modifier = modifier
            )
        }

        composable(SettingsDestinations.Library.route) {
            LibrarySettingScreen(onNavigateUp = { navController.navigateUp() }, modifier = modifier)
        }

        composable(SettingsDestinations.ThemesAndColor.route) {
            SettingThemeAndColorScreen(
                onNavigateUp = { navController.navigateUp() },
                modifier = modifier
            )
        }
    }
}


