package com.example.musicsample.feature.nav_bar_feature.genre

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.musicsample.core.components.presentation.FakeListItemForNavDrawer
import kotlin.math.roundToInt


enum class MusicItemDragAnchors {
    Start,
    Center,
    End,
}


@Composable
fun MusicGenreScreen(
    modifier: Modifier = Modifier
) {
    ListWithAnchoredDrag()
}

@Composable
fun MockBoxTextGenre(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "This is Genre Screen.")
    }
}

@Composable
fun ListWithAnchoredDrag(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        for (i in 1..100) {
            item {
                ItemsForList(
                    count = i
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsForList(
    count: Int,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val defaultActionBoxSize = 60.dp

    val startActionSizePx = with(density) { defaultActionBoxSize.toPx() }
    val endActionSizePx = with(density) { (defaultActionBoxSize * 2).toPx() } // for 2 buttons

    val anchors = DraggableAnchors<MusicItemDragAnchors> {
        MusicItemDragAnchors.Start at -startActionSizePx
        MusicItemDragAnchors.Center at 0f
        MusicItemDragAnchors.End at endActionSizePx
    }

    val anchoredDraggableState = remember {
        AnchoredDraggableState(
            initialValue = MusicItemDragAnchors.Center,
            anchors = anchors,
            positionalThreshold = { distance: Float -> distance * 0.9f },
            velocityThreshold = { with(receiver = density) { 100.dp.toPx() } },
            animationSpec = tween()
        )
    }

    DraggableMusicItem(
        modifier = modifier,
        state = anchoredDraggableState,
        startAction = {
            ActionMusic(
                icon = Icons.Default.Album,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                label = "Album",
                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                modifier = Modifier
                    .width(defaultActionBoxSize)
                    .offset {
                        IntOffset(
                            (-anchoredDraggableState
                                .requireOffset() - startActionSizePx)
                                .roundToInt(),
                            0
                        )
                    }
            )
        },
        endAction = {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .background(MaterialTheme.colorScheme.surfaceContainerLow)
            ) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(MaterialTheme.colorScheme.surfaceContainerLow)
                        .clickable(onClick = { })
                ) {
                    ActionMusic(
                        icon = Icons.Default.Edit,
                        backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                        label = "Edit",
                        shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                        modifier = Modifier
                            .width(defaultActionBoxSize)
                            .offset {
                                IntOffset(
                                    (-anchoredDraggableState
                                        .requireOffset() + startActionSizePx)
                                        .roundToInt(),
                                    0
                                )
                            }
                    )
                }
                ActionMusic(
                    icon = Icons.Default.SkipNext,
                    backgroundColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                    label = "Next",
                    shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                    modifier = Modifier
                        .width(defaultActionBoxSize)
                        .offset {
                            IntOffset(
                                ((-anchoredDraggableState
                                    .requireOffset() * 0.5f) + startActionSizePx)
                                    .roundToInt(),
                                0
                            )
                        }
                )
            }
        },
        content = {
            FakeListItemForNavDrawer(
                onArtistClick = { /*TODO*/ },
                showTail = true,
                color = ListItemDefaults.containerColor
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableMusicItem(
    state: AnchoredDraggableState<MusicItemDragAnchors>,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    endAction: @Composable (BoxScope.() -> Unit)? = {},
    startAction: @Composable (BoxScope.() -> Unit)? = {},
) {

    Box(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .clip(RectangleShape)
            .clickable(onClick = {})
    ) {

        endAction?.let {
            endAction()
        }

        startAction?.let {
            startAction()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .offset {
                    IntOffset(
                        x = -state
                            .requireOffset()
                            .roundToInt(),
                        y = 0,
                    )
                }
                .anchoredDraggable(state, Orientation.Horizontal, reverseDirection = true),
        ) {
            content()
        }
    }
}


@Composable
fun ActionMusic(
    icon: ImageVector,
    label: String,
    backgroundColor: Color,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(shape)
            .background(backgroundColor)
            .clickable(onClick = {}),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}
