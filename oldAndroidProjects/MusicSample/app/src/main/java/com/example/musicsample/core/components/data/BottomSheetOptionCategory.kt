package com.example.musicsample.core.components.data

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

data class BottomSheetOptionCategory(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)

val bottomSheetOptionCategoryForSongs: List<BottomSheetOptionCategory> = listOf(
    BottomSheetOptionCategory(
        label = "Play next",
        icon = Icons.Filled.SkipNext
    ),
    BottomSheetOptionCategory(
        label = "Add to playing queue",
        icon = Icons.Filled.AddToPhotos
    ),
    BottomSheetOptionCategory(
        label = "Add to playlist",
        icon = Icons.AutoMirrored.Filled.PlaylistAdd
    ),
    BottomSheetOptionCategory(
        label = "Go to album",
        icon = Icons.Filled.Album
    ),
    BottomSheetOptionCategory(
        label = "Go to artist",
        icon = Icons.Filled.Person
    ),
    BottomSheetOptionCategory(
        label = "Go to album artist",
        icon = Icons.Filled.Album
    ),
    BottomSheetOptionCategory(
        label = "Go to similar genre",
        icon = Icons.Filled.MusicNote
    ),
    BottomSheetOptionCategory(
        label = "Go to folder",
        icon = Icons.Filled.Folder
    ),
    BottomSheetOptionCategory(
        label = "Tag editor",
        icon = Icons.Filled.Edit
    ),
    BottomSheetOptionCategory(
        label = "Edit lyrics",
        icon = Icons.Filled.EditNote
    ),
    BottomSheetOptionCategory(
        label = "Blacklist",
        icon = Icons.Filled.FolderOff
    ),
    BottomSheetOptionCategory(
        label = "Details",
        icon = Icons.Filled.Info
    ),
    BottomSheetOptionCategory(
        label = "Share",
        icon = Icons.Filled.Share
    ),
    BottomSheetOptionCategory(
        label = "Delete from device",
        icon = Icons.Filled.DeleteForever
    )
)
