package com.example.musicsample.bottomsheet.player.mini_player

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.musicsample.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniPlayer(
    modifier: Modifier = Modifier
) {
    var miniPlayerHeight by remember { mutableIntStateOf(0) }
    val localDensity = LocalDensity.current

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    val scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
//            ExpandedNowPlayingSheet()
            Column(modifier = Modifier.onGloballyPositioned { miniPlayerHeight = it.size.height }) {
                MiniPlayerContent()
                MiniPlayerProgressBar()
            }
        },
        sheetPeekHeight = with(localDensity) { miniPlayerHeight.toDp() },
        sheetDragHandle = null,
        sheetContainerColor = BottomAppBarDefaults.containerColor,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetSwipeEnabled = true,
        sheetTonalElevation = BottomAppBarDefaults.ContainerElevation,
        sheetShadowElevation = BottomSheetDefaults.Elevation,
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        contentColor = contentColorFor(backgroundColor = BottomSheetDefaults.ContainerColor)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Scaffold Content")
        }
    }
}

@Composable
fun MiniPlayerProgressBar(
    modifier: Modifier = Modifier
) {
    LinearProgressIndicator(
        progress = 0.4f,
        modifier = modifier
            .fillMaxWidth()
            .size(1.dp)
    )
}

@Composable
fun MiniPlayerContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_padding)),
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.small_padding),
                top = dimensionResource(id = R.dimen.extra_small_padding),
                bottom = dimensionResource(id = R.dimen.extra_small_padding),
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.album_cover),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.small_padding)))
        )
        MiniPlayerSongDetail(modifier = Modifier.weight(1f))
        MiniPlayerControlsWithIconButton(modifier = Modifier)
    }
}

@Composable
fun MiniPlayerControlsWithIconButton(
    modifier: Modifier = Modifier,
) {
    var isPlaying by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        modifier = modifier
    ) {
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Outlined.SkipPrevious, contentDescription = "Play Previous",
                modifier = modifier.size(28.dp)
            )
        }

        IconButton(onClick = { isPlaying = !isPlaying }) {
            if (isPlaying) {
                Icon(
                    imageVector = Icons.Outlined.Pause, contentDescription = "Pause",
                    modifier = modifier.size(28.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.PlayArrow, contentDescription = "Play",
                    modifier = modifier.size(28.dp)
                )
            }
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.SkipNext, contentDescription = "Play Next",
                modifier = modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun MiniPlayerControlsOnlyIcon(
    modifier: Modifier = Modifier,
) {
    var isPlaying by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(end = dimensionResource(id = R.dimen.small_padding))
    ) {

        Icon(
            imageVector = Icons.Outlined.SkipPrevious, contentDescription = "Play Previous",
            modifier = modifier
                .size(28.dp)
                .clickable(onClick = {})
        )

        if (isPlaying) {
            Icon(
                imageVector = Icons.Outlined.Pause, contentDescription = "Pause",
                modifier = modifier
                    .size(28.dp)
                    .clickable(onClick = { isPlaying = !isPlaying })
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.PlayArrow, contentDescription = "Play",
                modifier = modifier
                    .size(28.dp)
                    .clickable(onClick = { isPlaying = !isPlaying })
            )
        }

        Icon(
            imageVector = Icons.Outlined.SkipNext, contentDescription = "Play Next",
            modifier = modifier
                .size(28.dp)
                .clickable(onClick = {})
        )
    }
}

@Composable
fun MiniPlayerSongDetail(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "Follow You (Summer'21 Version)",
            maxLines = 1,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "Follow You / Cutthroat",
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall
        )
    }
}