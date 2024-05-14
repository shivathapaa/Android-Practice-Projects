package com.example.musicsample.core.components.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.musicsample.R
import com.example.musicsample.core.components.data.BottomSheetOptionCategory
import com.example.musicsample.core.components.data.bottomSheetOptionCategoryForSongs
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicSampleBottomSheet(
    openBottomSheet: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = { openBottomSheet(false) },
        dragHandle = null,
        windowInsets = WindowInsets(bottom = 0),
    ) {
        BottomSheetNowPlayingListItem(modifier = Modifier)
        Divider()
        BottomSheetContent(
            bottomSheetState = bottomSheetState,
            openBottomSheetState = { openBottomSheet(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    bottomSheetState: SheetState,
    openBottomSheetState: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetItemList: List<BottomSheetOptionCategory> = bottomSheetOptionCategoryForSongs,
    dividerPlacement: List<Int> = listOf(2, 7, 9)
) {
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier.padding(
            top = dimensionResource(id = R.dimen.extra_small_padding),
            bottom = dimensionResource(id = R.dimen.medium_padding)
        )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheetNowPlayingListItem(
    modifier: Modifier = Modifier,
    onLikeClick: () -> Unit = {},
    onAlbumClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .clickable(onClick = onAlbumClick)
            .padding(
                horizontal = dimensionResource(id = R.dimen.medium_padding),
                vertical = dimensionResource(id = R.dimen.small_padding)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding)),

        ) {
        Image(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.bottom_sheet_album_art_size))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.small_padding))),
            painter = painterResource(id = R.drawable.album_cover),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Imagine Dragons",
                maxLines = 1,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Follow You (Summer'21 Version) (Album - Cutthroat)",
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.basicMarquee()
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
                    .size(dimensionResource(id = R.dimen.bottom_sheet_favourite_icon_size))
            )
        }
    }
}