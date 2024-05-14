package com.example.packagem3.core.components

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.AddToPhotos
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOff
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SkipNext
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.packagem3.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(
    openBottomSheet: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { openBottomSheet(false) },
        dragHandle = null,
        windowInsets = WindowInsets(bottom = 0),
        modifier = modifier
    ) {
        MyBottomSheetNowPlayingListItem(modifier = Modifier)
        HorizontalDivider()
        MyBottomSheetContentWithOptions(
            bottomSheetState = bottomSheetState,
            openBottomSheetState = { openBottomSheet(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheetContentWithOptions(
    bottomSheetState: SheetState,
    openBottomSheetState: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetItemList: List<MyBottomSheetOptions> = bottomSheetSongOptionList,
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
                in dividerPlacement -> HorizontalDivider(
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
fun MyBottomSheetNowPlayingListItem(
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


data class MyBottomSheetOptions(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)

val bottomSheetSongOptionList: List<MyBottomSheetOptions> = listOf(
    MyBottomSheetOptions(
        label = "Play next",
        icon = Icons.Filled.SkipNext
    ),
    MyBottomSheetOptions(
        label = "Add to playing queue",
        icon = Icons.Filled.AddToPhotos
    ),
    MyBottomSheetOptions(
        label = "Add to playlist",
        icon = Icons.AutoMirrored.Filled.PlaylistAdd
    ),
    MyBottomSheetOptions(
        label = "Go to album",
        icon = Icons.Filled.Album
    ),
    MyBottomSheetOptions(
        label = "Go to artist",
        icon = Icons.Filled.Person
    ),
    MyBottomSheetOptions(
        label = "Go to album artist",
        icon = Icons.Filled.Album
    ),
    MyBottomSheetOptions(
        label = "Go to similar genre",
        icon = Icons.Filled.MusicNote
    ),
    MyBottomSheetOptions(
        label = "Go to folder",
        icon = Icons.Filled.Folder
    ),
    MyBottomSheetOptions(
        label = "Tag editor",
        icon = Icons.Filled.Edit
    ),
    MyBottomSheetOptions(
        label = "Edit lyrics",
        icon = Icons.Filled.EditNote
    ),
    MyBottomSheetOptions(
        label = "Blacklist",
        icon = Icons.Filled.FolderOff
    ),
    MyBottomSheetOptions(
        label = "Details",
        icon = Icons.Filled.Info
    ),
    MyBottomSheetOptions(
        label = "Share",
        icon = Icons.Filled.Share
    ),
    MyBottomSheetOptions(
        label = "Delete from device",
        icon = Icons.Filled.DeleteForever
    )
)
