package com.example.musicsample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicsample.navigation.nav_drawer.NavDrawerNavigationHost

@Composable
fun MusicSampleApp(
    modifier: Modifier = Modifier
) {
    NavDrawerNavigationHost(
        modifier = modifier
    )
}