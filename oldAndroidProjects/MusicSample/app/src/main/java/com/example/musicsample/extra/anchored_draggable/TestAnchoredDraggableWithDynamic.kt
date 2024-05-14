package com.example.musicsample.extra.anchored_draggable

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.musicsample.R
import kotlin.math.roundToInt

enum class DragAnchorsDynamic(val fraction: Float) {
    Start(0f),
    Half(.5f),
    End(1f),
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalDraggableSampleDynamic(
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchorsDynamic.Half,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween(),
        )
    }
    val contentSize = 80.dp
    val contentSizePx = with(density) { contentSize.toPx() }
    Box(
        modifier
            .fillMaxWidth()
            .onSizeChanged { layoutSize ->
                val dragEndPoint = layoutSize.width - contentSizePx
                state.updateAnchors(
                    DraggableAnchors {
                        DragAnchorsDynamic.entries
                            .forEach { anchor ->
                                anchor at dragEndPoint * anchor.fraction
                            }
                    }
                )
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.album_cover),
            modifier = Modifier
                .size(contentSize)
                .offset {
                    IntOffset(
                        x = state
                            .requireOffset()
                            .roundToInt(),
                        y = 0,
                    )
                }
                .anchoredDraggable(state, Orientation.Horizontal),
            contentDescription = null,
        )
    }
}