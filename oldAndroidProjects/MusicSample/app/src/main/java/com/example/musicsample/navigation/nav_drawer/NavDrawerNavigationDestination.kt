package com.example.musicsample.navigation.nav_drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.PlaylistPlay
import androidx.compose.material.icons.outlined.Equalizer
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Scanner
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.musicsample.R

val listOfNavDrawerDestination: List<NavDrawerNavigationDestination> =
    listOf(
        LibraryDestination,
        PlayingQueueDestination,
        FoldersDestination,
        EqualizerDestination,
        ScanMediaDestination,
        SettingsDestination,
        AboutDestination
    )

interface NavDrawerNavigationDestination {
    val route: String
    val titleRes: Int
    val icon: ImageVector
    val onClick: () -> Unit
}

object LibraryDestination : NavDrawerNavigationDestination {
    override val route: String = "library"
    override val titleRes: Int = R.string.library
    override val icon: ImageVector = Icons.Outlined.LibraryMusic
    override val onClick: () -> Unit = {}
}

object FoldersDestination : NavDrawerNavigationDestination {
    override val route: String = "folders"
    override val titleRes: Int = R.string.folders
    override val icon: ImageVector = Icons.Outlined.LibraryMusic
    override val onClick: () -> Unit = {}
}

object PlayingQueueDestination : NavDrawerNavigationDestination {
    override val route: String = "playing_queue"
    override val titleRes: Int = R.string.playing_queue
    override val icon: ImageVector = Icons.AutoMirrored.Outlined.PlaylistPlay
    override val onClick: () -> Unit = {}
}

object EqualizerDestination : NavDrawerNavigationDestination {
    override val route: String = "equalizer"
    override val titleRes: Int = R.string.equalizer
    override val icon: ImageVector = Icons.Outlined.Equalizer
    override val onClick: () -> Unit = {}
}

object ScanMediaDestination : NavDrawerNavigationDestination {
    override val route: String = "scan_media"
    override val titleRes: Int = R.string.scan_media
    override val icon: ImageVector = Icons.Outlined.Scanner
    override val onClick: () -> Unit = {}
}

object SettingsDestination : NavDrawerNavigationDestination {
    override val route: String = "settings"
    override val titleRes: Int = R.string.settings
    override val icon: ImageVector = Icons.Outlined.Settings
    override val onClick: () -> Unit = {}
}

object AboutDestination : NavDrawerNavigationDestination {
    override val route: String = "about"
    override val titleRes: Int = R.string.about
    override val icon: ImageVector = Icons.Outlined.Info
    override val onClick: () -> Unit = {}
}