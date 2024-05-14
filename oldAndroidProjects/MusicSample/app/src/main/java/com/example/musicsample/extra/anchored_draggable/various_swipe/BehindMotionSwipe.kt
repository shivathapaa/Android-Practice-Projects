package com.example.musicsample.extra.anchored_draggable.various_swipe

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BehindMotionSwipe() {
    val density = LocalDensity.current
    val defaultActionSize = 80.dp
    val endActionSizePx = with(density) { (defaultActionSize * 2).toPx() }
    val startActionSizePx = with(density) { defaultActionSize.toPx() }

    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchorsSwipe.Center,
            anchors = DraggableAnchors {
                DragAnchorsSwipe.Start at -startActionSizePx
                DragAnchorsSwipe.Center at 0f
                DragAnchorsSwipe.End at endActionSizePx
            },
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween()
        )
    }

    DraggableItem(
        state = state,
        content = { HelloWorldCard(name = "Behind Motion Swipe") },
        startAction = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterStart),
            ) {
                SaveAction(
                    Modifier
                        .width(defaultActionSize)
                        .fillMaxHeight()
                )
            }
        },
        endAction = {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                EditAction(
                    modifier = Modifier
                        .width(defaultActionSize)
                        .fillMaxHeight()
                )
                DeleteAction(
                    Modifier
                        .width(defaultActionSize)
                        .fillMaxHeight()
                )
            }
        }
    )

}