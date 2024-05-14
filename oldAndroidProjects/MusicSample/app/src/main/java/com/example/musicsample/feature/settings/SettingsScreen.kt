package com.example.musicsample.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.example.musicsample.R
import com.example.musicsample.navigation.nav_drawer.settings.SettingsDestinations
import com.example.musicsample.navigation_drawer.presentation.SettingsScaffold

@Composable
fun SettingsScreenContents(
    onLibraryClick: () -> Unit,
    onNowPlayingClick: () -> Unit,
    onThemesAndColorClick: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsScaffold(title = "Settings", onNavigateUp = onNavigateUp) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = dimensionResource(id = R.dimen.large_padding))
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            SettingsItemList(
                title = SettingsDestinations.Library.title,
                icon = SettingsDestinations.Library.icon,
                description = SettingsDestinations.Library.description,
                modifier = Modifier.clickable(onClick = onLibraryClick)
            )
            SettingsItemList(
                title = SettingsDestinations.NowPlaying.title,
                icon = SettingsDestinations.NowPlaying.icon,
                description = SettingsDestinations.NowPlaying.description,
                modifier = Modifier.clickable(onClick = onNowPlayingClick)
            )
            SettingsItemList(
                title = SettingsDestinations.ThemesAndColor.title,
                icon = SettingsDestinations.ThemesAndColor.icon,
                description = SettingsDestinations.ThemesAndColor.description,
                modifier = Modifier.clickable(onClick = onThemesAndColorClick)
            )
        }
    }
}

@Composable
fun SettingsItemList(
    title: String,
    icon: ImageVector,
    description: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier,
        headlineContent = { Text(text = title) },
        supportingContent = { Text(text = description) },
        leadingContent = {
            Icon(imageVector = icon, contentDescription = null)
        }
    )
}