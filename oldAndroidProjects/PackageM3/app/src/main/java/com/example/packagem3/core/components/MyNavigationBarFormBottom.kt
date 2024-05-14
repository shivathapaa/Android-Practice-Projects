package com.example.packagem3.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistPlay
import androidx.compose.material.icons.automirrored.outlined.PlaylistPlay
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Album
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MyNavigationBarFormBottom(
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableIntStateOf(0) }
    val categories = categoryListOfMyNavigationBar

    NavigationBar(
        modifier = modifier
    ) {
        categories.take(5).forEachIndexed { index, category ->
            NavigationBarItem(
                selected = selectedCategory == index,
                onClick = {
                    selectedCategory = index
                    category.onClick
                },
                icon = {
                    if (selectedCategory != index) {
                        Icon(imageVector = category.outlineIcon, contentDescription = null)
                    } else {
                        Icon(imageVector = category.filledIcon, contentDescription = null)
                    }
                },
                label = { Text(text = category.label) },
                alwaysShowLabel = false
            )
        }
    }
}


data class MyNavigationBarCategory(
    val label: String,
    val outlineIcon: ImageVector,
    val filledIcon: ImageVector,
    val onClick: () -> Unit,
    val selected: Boolean
)

val categoryListOfMyNavigationBar: MutableList<MyNavigationBarCategory> = mutableListOf(
    MyNavigationBarCategory(
        label = "Home",
        outlineIcon = Icons.Outlined.Home,
        filledIcon = Icons.Filled.Home,
        onClick = { },
        selected = true
    ),
    MyNavigationBarCategory(
        label = "Songs",
        outlineIcon = Icons.Outlined.MusicNote,
        filledIcon = Icons.Filled.MusicNote,
        onClick = { },
        selected = true
    ),
    MyNavigationBarCategory(
        label = "Artists",
        outlineIcon = Icons.Outlined.Person,
        filledIcon = Icons.Filled.Person,
        onClick = { },
        selected = true
    ),
    MyNavigationBarCategory(
        label = "Albums",
        outlineIcon = Icons.Outlined.Album,
        filledIcon = Icons.Filled.Album,
        onClick = { },
        selected = true
    ),
    MyNavigationBarCategory(
        label = "Genres",
        outlineIcon = Icons.Outlined.MusicNote,
        filledIcon = Icons.Filled.MusicNote,
        onClick = { },
        selected = true
    ),
    MyNavigationBarCategory(
        label = "Playlists",
        outlineIcon = Icons.AutoMirrored.Outlined.PlaylistPlay,
        filledIcon = Icons.AutoMirrored.Filled.PlaylistPlay,
        onClick = { },
        selected = false
    ),
    MyNavigationBarCategory(
        label = "Folders",
        outlineIcon = Icons.Outlined.Folder,
        filledIcon = Icons.Filled.Folder,
        onClick = { },
        selected = false
    )
)