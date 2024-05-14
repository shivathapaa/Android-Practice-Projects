package com.example.packagem3.ui.presentation.bottomsheet

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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.packagem3.R
import kotlinx.coroutines.launch

@Composable
fun MyBottomSheetScreen(
    modifier: Modifier = Modifier
) {
    MySimpleModalBottomSheet(modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySimpleModalBottomSheet(
    modifier: Modifier = Modifier
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var openScaffoldBottomSheet by rememberSaveable { mutableStateOf(false) }

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { openBottomSheet = !openBottomSheet }) {
            Text("Open simple bs")
        }
        Button(onClick = { openScaffoldBottomSheet = !openScaffoldBottomSheet }) {
            Text("Open scaffold bs")
        }
    }

    if (openScaffoldBottomSheet) {
        MyBottomSheetScaffoldScreen()
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
            MyBottomSheetContents(
                bottomSheetState = bottomSheetState,
                openBottomSheetState = { openBottomSheet = it }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheetContents(
    bottomSheetState: SheetState,
    openBottomSheetState: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetItemList: List<OnMoreBottomSheetOptions> = songBottomSheetItemList,
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

//    ListItem(
//        modifier = modifier.clickable(onClick = onAlbumClick),
//        headlineContent = {
//            Text(
//                text = "Follow You (Summer'21 Version)",
//                maxLines = 1,
//                style = MaterialTheme.typography.bodyMedium
//            )
//        },
//        supportingContent = {
//            Text(
//                text = "Follow You / Cutthroat",
//                maxLines = 1,
//                style = MaterialTheme.typography.bodySmall
//            )
//        },
//        overlineContent = {
//            Text(
//                text = "Imagine Dragons",
//                maxLines = 1,
//                style = MaterialTheme.typography.labelSmall
//            )
//        },
//        leadingContent = {
//            Image(
//                painter = painterResource(id = R.drawable.album_cover),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(56.dp)
//                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.small_padding)))
//            )
//        },
//        trailingContent = {
//            IconButton(
//                onClick = onLikeClick
//            ) {
//                Icon(
//                    imageVector = Icons.Default.FavoriteBorder,
//                    contentDescription = "Add to favourites",
//                    modifier = Modifier
//                        .size(24.dp)
//                )
//            }
//        }
//    )
}


data class OnMoreBottomSheetOptions(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)

val songBottomSheetItemList: List<OnMoreBottomSheetOptions> = listOf(
    OnMoreBottomSheetOptions(
        label = "Play next",
        icon = Icons.Filled.SkipNext
    ),
    OnMoreBottomSheetOptions(
        label = "Add to playing queue",
        icon = Icons.Filled.AddToPhotos
    ),
    OnMoreBottomSheetOptions(
        label = "Add to playlist",
        icon = Icons.AutoMirrored.Filled.PlaylistAdd
    ),
    OnMoreBottomSheetOptions(
        label = "Go to album",
        icon = Icons.Filled.Album
    ),
    OnMoreBottomSheetOptions(
        label = "Go to artist",
        icon = Icons.Filled.Person
    ),
    OnMoreBottomSheetOptions(
        label = "Go to album artist",
        icon = Icons.Filled.Album
    ),
    OnMoreBottomSheetOptions(
        label = "Go to similar genre",
        icon = Icons.Filled.MusicNote
    ),
    OnMoreBottomSheetOptions(
        label = "Go to folder",
        icon = Icons.Filled.Folder
    ),
    OnMoreBottomSheetOptions(
        label = "Tag editor",
        icon = Icons.Filled.Edit
    ),
    OnMoreBottomSheetOptions(
        label = "Edit lyrics",
        icon = Icons.Filled.EditNote
    ),
    OnMoreBottomSheetOptions(
        label = "Blacklist",
        icon = Icons.Filled.FolderOff
    ),
    OnMoreBottomSheetOptions(
        label = "Details",
        icon = Icons.Filled.Info
    ),
    OnMoreBottomSheetOptions(
        label = "Share",
        icon = Icons.Filled.Share
    ),
    OnMoreBottomSheetOptions(
        label = "Delete from device",
        icon = Icons.Filled.DeleteForever
    )
)


@Composable
fun BottomSheetAlbumDirectorListItemBroughtLayoutUnevenness(
    modifier: Modifier = Modifier,
    onLikeClick: () -> Unit = {},
    onAlbumClick: () -> Unit = {},
) {
    ListItem(
        modifier = modifier.clickable(onClick = onAlbumClick),
        headlineContent = {
            Text(
                text = "Follow You (Summer'21 Version)",
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        supportingContent = {
            Text(
                text = "Follow You / Cutthroat",
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall
            )
        },
        overlineContent = {
            Text(
                text = "Imagine Dragons",
                maxLines = 1,
                style = MaterialTheme.typography.labelSmall
            )
        },
        leadingContent = {
            Image(
                painter = painterResource(id = R.drawable.album_cover),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.small_padding)))
            )
        },
        trailingContent = {
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
    )
}