package com.example.musicsample.bottomsheet.player.expanded_player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.QueueMusic
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Lyrics
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.PauseCircleFilled
import androidx.compose.material.icons.outlined.PlayCircleFilled
import androidx.compose.material.icons.outlined.Repeat
import androidx.compose.material.icons.outlined.Shuffle
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.musicsample.R

@Composable
fun ExpandedNowPlayingSheet(
    modifier: Modifier = Modifier,
    visibility: Float = 1f,
) {
    var showDetail by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .alpha(visibility)
    ) {
        NowPlayingAlbumWithTitle()
        Spacer(modifier = Modifier.weight(1f))
        SeekBarControlWithDuration()
        ExpandedPlayerControls(modifier = Modifier)
        Spacer(modifier = Modifier.weight(1f))
        AnimatedVisibility(visible = showDetail) { SongDetail(modifier) }
        BottomControls(showDetail = { showDetail = !showDetail })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NowPlayingAlbumWithTitle(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .safeDrawingPadding()
            .padding(
                bottom = dimensionResource(id = R.dimen.very_extra_large_padding),
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.album_cover), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.large_padding))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.medium_padding)))
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))

        Text(
            text = "Follow You (Summer'21 Version)(Summer'21 Version)",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.extra_large_padding),
                    end = dimensionResource(id = R.dimen.extra_large_padding),
                    bottom = dimensionResource(id = R.dimen.extra_small_padding)
                )
                .basicMarquee()
        )
        Text(
            text = "Imagine Dragon",
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.extra_large_padding)
                )
        )
    }
}

@Composable
fun SeekBarControlWithDuration(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding)),
        modifier = modifier.fillMaxWidth()
    ) {
        LinearProgressIndicator(
            strokeCap = StrokeCap.Round,
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.extra_large_padding))
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.extra_large_padding))
        ) {
            Text(
                text = "02:22",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = "04:56",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun ExpandedPlayerControls(
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.large_padding),
                horizontal = dimensionResource(id = R.dimen.large_padding)
            )
    ) {

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.Repeat, contentDescription = "Repeat",
                modifier = modifier
                    .size(32.dp)
            )
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.SkipPrevious, contentDescription = "Play Previous",
                modifier = modifier
                    .size(36.dp)
            )
        }

        IconButton(
            onClick = { isPlaying = !isPlaying },
            modifier = modifier
                .size(80.dp)
        ) {
            if (isPlaying) {
                Icon(
                    imageVector = Icons.Outlined.PauseCircleFilled, contentDescription = "Pause",
                    modifier = modifier
                        .size(72.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.PlayCircleFilled, contentDescription = "Play",
                    modifier = modifier
                        .size(72.dp)
                )
            }
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.SkipNext, contentDescription = "Play Next",
                modifier = modifier
                    .size(36.dp)
            )
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.Shuffle, contentDescription = "Shuffle",
                modifier = modifier
                    .size(32.dp)
            )
        }
    }
}

@Composable
fun SongDetail(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.medium_padding),
                horizontal = dimensionResource(id = R.dimen.large_padding)
            )
    ) {
        Text(
            text = "FLAC • 710 kb/s • 44.1kHz",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun BottomControls(
    showDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.medium_padding),
                horizontal = dimensionResource(id = R.dimen.large_padding)
            )
    ) {
        IconButton(onClick = { showDetail() }) {
            Icon(imageVector = Icons.Outlined.MoreHoriz, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(4.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.Timer, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(4.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.Favorite, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(4.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.AutoMirrored.Outlined.QueueMusic, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(4.dp))
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Outlined.Lyrics, contentDescription = null)
        }
    }
}