package com.example.musicsample.extra.motion_layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyMotionLayoutDemo(
    modifier: Modifier = Modifier
) {

}

enum class SwipingStates {//our own enum class for stoppages e.g. expanded and collapsed
EXPANDED,
    COLLAPSED
}
