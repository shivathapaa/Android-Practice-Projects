package com.example.musicsample.core.components.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.PlaylistPlay
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Album
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.SupervisorAccount
import androidx.compose.ui.graphics.vector.ImageVector

data class FilterMusicChipCategory(
    val onCategoryClick: () -> Unit,
    val icon: ImageVector,
    val categoryLabel: String
)


val listOfChipsCategory: List<FilterMusicChipCategory> = listOf<FilterMusicChipCategory>(
    FilterMusicChipCategory(
        onCategoryClick = {},
        icon = Icons.Rounded.DoneAll,
        categoryLabel = "All"
    ),
    FilterMusicChipCategory(
        onCategoryClick = {},
        icon = Icons.Rounded.MusicNote,
        categoryLabel = "Songs"
    ),
    FilterMusicChipCategory(
        onCategoryClick = {},
        icon = Icons.Rounded.Album,
        categoryLabel = "Albums"
    ),
    FilterMusicChipCategory(
        onCategoryClick = {},
        icon = Icons.Rounded.AccountCircle,
        categoryLabel = "Albums"
    ),
    FilterMusicChipCategory(
        onCategoryClick = {},
        icon = Icons.Rounded.SupervisorAccount,
        categoryLabel = "Album Artists"
    ),
    FilterMusicChipCategory(
        onCategoryClick = {},
        icon = Icons.Rounded.MusicNote,
        categoryLabel = "Genre"
    ),
    FilterMusicChipCategory(
        onCategoryClick = {},
        icon = Icons.AutoMirrored.Rounded.PlaylistPlay,
        categoryLabel = "Playlists"
    ),
)