package com.example.musicsample.feature.settings.now_playing

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicsample.feature.nav_bar_feature.genre.ItemsForList
import com.example.musicsample.navigation.nav_drawer.settings.SettingsDestinations
import com.example.musicsample.navigation_drawer.presentation.SettingsScaffold

@Composable
fun NowPlayingQueueListScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsScaffold(title = SettingsDestinations.NowPlaying.title, onNavigateUp = onNavigateUp) {innerPadding ->
        LazyColumn(
            modifier = modifier.fillMaxSize().padding(innerPadding)
        ) {
            item {
                Text(text = "This is now playing queue.")
            }
            for (i in 1..100) {
                item {
                    ItemsForList(
                        count = i
                    )
                }
            }
        }
    }
}