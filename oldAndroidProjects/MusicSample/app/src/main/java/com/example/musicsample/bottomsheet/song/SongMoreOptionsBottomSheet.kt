package com.example.musicsample.bottomsheet.song

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicsample.R
import com.example.musicsample.bottomsheet.player.mini_player.MiniPlayer
import com.example.musicsample.bottomsheet.song.data.OnMoreSongBottomSheetOptions
import com.example.musicsample.bottomsheet.song.data.songBottomSheetItemList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongMoreOptionsBottomSheet(
    modifier: Modifier = Modifier
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var openScaffoldBottomSheet by rememberSaveable { mutableStateOf(false) }

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { openBottomSheet = !openBottomSheet }) {
            Text("Open simple bs")
        }
        Button(onClick = { openScaffoldBottomSheet = !openScaffoldBottomSheet }) {
            Text("Open scaffold bs")
        }
    }

    if (openScaffoldBottomSheet) {
        MiniPlayer()
    }


    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
            dragHandle = null,
            windowInsets = WindowInsets(bottom = 0),
            modifier = modifier
        ) {
            BottomSheetAlbumDirector(modifier = Modifier)
            HorizontalDivider()
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
            SongBottomSheetContents(
                bottomSheetState = bottomSheetState,
                openBottomSheetState = { openBottomSheet = it }
            )
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongBottomSheetContents(
    bottomSheetState: SheetState,
    openBottomSheetState: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetItemList: List<OnMoreSongBottomSheetOptions> = songBottomSheetItemList,
    dividerPlacement: List<Int> = listOf(2, 7, 9)
) {
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(bottomSheetItemList) { index, bottomSheetOption ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding)),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                            // you must additionally handle intended state cleanup, if any.
                            scope
                                .launch { bottomSheetState.hide() }
                                .invokeOnCompletion {
                                    if (!bottomSheetState.isVisible) {
                                        openBottomSheetState(false)
                                    }
                                }
                            bottomSheetOption.onClick
                        })
                    .padding(
                        start = dimensionResource(id = R.dimen.large_padding),
                        end = dimensionResource(id = R.dimen.medium_padding),
                        bottom = dimensionResource(id = R.dimen.small_padding),
                        top = dimensionResource(id = R.dimen.small_padding)
                    )
            ) {
                Icon(
                    imageVector = bottomSheetOption.icon,
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.bottom_sheet_icon_size))
                )
                Text(
                    text = bottomSheetOption.label,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            when (index) {
                in dividerPlacement -> Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.bottom_sheet_horizontal_divider_start_padding),
                            top = dimensionResource(id = R.dimen.small_padding),
                            bottom = dimensionResource(id = R.dimen.small_padding)
                        )
                )
            }
        }
    }
}


@Composable
fun BottomSheetAlbumDirector(
    modifier: Modifier = Modifier,
    onLikeClick: () -> Unit = {},
    onAlbumClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding)),
        modifier = modifier
            .clickable(onClick = onAlbumClick)
            .padding(
                horizontal = dimensionResource(id = R.dimen.medium_padding),
                vertical = dimensionResource(id = R.dimen.small_padding)
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.album_cover),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.small_padding)))
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Imagine Dragons",
                maxLines = 1,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Follow You (Summer'21 Version)",
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Follow You / Cutthroat",
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall
            )
        }
        IconButton(
            onClick = onLikeClick
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Add to favourites",
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}