package com.example.musicsample.feature.nav_bar_feature.album

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.musicsample.extra.anchored_draggable.HorizontalDraggableSample
import com.example.musicsample.extra.anchored_draggable.HorizontalDraggableSampleDynamic
import com.example.musicsample.extra.anchored_draggable.various_swipe.BehindMotionSwipe
import com.example.musicsample.extra.anchored_draggable.various_swipe.DrawerMotionSwipeDemo
import com.example.musicsample.extra.anchored_draggable.various_swipe.ScrollMotionSwipeDemo

@Composable
fun MusicAlbumScreen(
    modifier: Modifier = Modifier
) {
//    MockBoxText()
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        HorizontalDraggableSample()
        HorizontalDraggableSampleDynamic()
        BehindMotionSwipe()
        ScrollMotionSwipeDemo()
        DrawerMotionSwipeDemo()
    }
}

@Composable
fun MockBoxText(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "This is Album Screen.")
    }
}