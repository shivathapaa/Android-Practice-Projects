package com.example.musicsample.bottomsheet.song.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.AddToPhotos
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOff
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.ui.graphics.vector.ImageVector

data class OnMoreSongBottomSheetOptions(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)

val songBottomSheetItemList: List<OnMoreSongBottomSheetOptions> = listOf(
    OnMoreSongBottomSheetOptions(
        label = "Play next",
        icon = Icons.Filled.SkipNext
    ),
    OnMoreSongBottomSheetOptions(
        label = "Add to playing queue",
        icon = Icons.Filled.AddToPhotos
    ),
    OnMoreSongBottomSheetOptions(
        label = "Add to playlist",
        icon = Icons.AutoMirrored.Filled.PlaylistAdd
    ),
    OnMoreSongBottomSheetOptions(
        label = "Go to album",
        icon = Icons.Filled.Album
    ),
    OnMoreSongBottomSheetOptions(
        label = "Go to artist",
        icon = Icons.Filled.Person
    ),
    OnMoreSongBottomSheetOptions(
        label = "Go to album artist",
        icon = Icons.Filled.Album
    ),
    OnMoreSongBottomSheetOptions(
        label = "Go to similar genre",
        icon = Icons.Filled.MusicNote
    ),
    OnMoreSongBottomSheetOptions(
        label = "Go to folder",
        icon = Icons.Filled.Folder
    ),
    OnMoreSongBottomSheetOptions(
        label = "Tag editor",
        icon = Icons.Filled.Edit
    ),
    OnMoreSongBottomSheetOptions(
        label = "Edit lyrics",
        icon = Icons.Filled.EditNote
    ),
    OnMoreSongBottomSheetOptions(
        label = "Blacklist",
        icon = Icons.Filled.FolderOff
    ),
    OnMoreSongBottomSheetOptions(
        label = "Details",
        icon = Icons.Filled.Info
    ),
    OnMoreSongBottomSheetOptions(
        label = "Share",
        icon = Icons.Filled.Share
    ),
    OnMoreSongBottomSheetOptions(
        label = "Delete from device",
        icon = Icons.Filled.DeleteForever
    )
)
