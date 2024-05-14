package com.example.musicsample.navigation.nav_drawer.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistPlay
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.ui.graphics.vector.ImageVector

sealed class SettingsDestinations(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val description: String
) {
    data object Library : SettingsDestinations(
        route = "setting_library",
        title = "Library",
        icon = Icons.Default.LibraryMusic,
        description = "Filters, navigation, director."
    )

    data object NowPlaying : SettingsDestinations(
        route = "now_playing",
        title = "Now Playing",
        icon = Icons.AutoMirrored.Filled.PlaylistPlay,
        description = "Manage now playing."
    )

    data object ThemesAndColor :
        SettingsDestinations(
            route = "themes_and_color",
            title = "Themes & Color",
            icon = Icons.Default.ColorLens,
            description = "Personalize you experience."
        )
}

val settingsDestinationList = listOf(
    SettingsDestinations.Library,
    SettingsDestinations.NowPlaying,
    SettingsDestinations.ThemesAndColor
)