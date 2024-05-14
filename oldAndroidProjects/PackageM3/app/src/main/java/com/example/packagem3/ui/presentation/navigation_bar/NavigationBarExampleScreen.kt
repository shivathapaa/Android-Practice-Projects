package com.example.packagem3.ui.presentation.navigation_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistPlay
import androidx.compose.material.icons.automirrored.outlined.PlaylistPlay
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Favorite
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
fun NavigationBarExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        MySimpleNavigationBar()
        MyNavigationBarWithOnlySelectedLabels()
        MyNavigationBarWithOnlySelectedLabelsAndReordering()
    }
}

@Composable
fun MySimpleNavigationBar(
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf<String>("Home", "Song", "Album", "Artist", "Playlists")

    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = item
                    )
                },
                label = { Text(text = item) }
            )
        }
    }

}

@Composable
fun MyNavigationBarWithOnlySelectedLabels(
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = itemListOfMyNavigationBar

    NavigationBar(
        modifier = modifier
    ) {
        items.take(5).forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = {
                    Icon(
                        imageVector = item.filledIcon,
                        contentDescription = item.label
                    )
                },
                label = { Text(text = item.label) },
                alwaysShowLabel = false
            )
        }
    }
}

data class MyNavigationBarItems(
    val label: String,
    val outlineIcon: ImageVector,
    val filledIcon: ImageVector,
    val onClick: () -> Unit,
    val selected: Boolean
)

val itemListOfMyNavigationBar: MutableList<MyNavigationBarItems> = mutableListOf(
    MyNavigationBarItems(
        label = "Home",
        outlineIcon = Icons.Outlined.Home,
        filledIcon = Icons.Filled.Home,
        onClick = { },
        selected = true
    ),
    MyNavigationBarItems(
        label = "Songs",
        outlineIcon = Icons.Outlined.MusicNote,
        filledIcon = Icons.Filled.MusicNote,
        onClick = { },
        selected = true
    ),
    MyNavigationBarItems(
        label = "Artists",
        outlineIcon = Icons.Outlined.Person,
        filledIcon = Icons.Filled.Person,
        onClick = { },
        selected = true
    ),
    MyNavigationBarItems(
        label = "Albums",
        outlineIcon = Icons.Outlined.Album,
        filledIcon = Icons.Filled.Album,
        onClick = { },
        selected = true
    ),
    MyNavigationBarItems(
        label = "Genres",
        outlineIcon = Icons.Outlined.MusicNote,
        filledIcon = Icons.Filled.MusicNote,
        onClick = { },
        selected = true
    ),
    MyNavigationBarItems(
        label = "Playlists",
        outlineIcon = Icons.AutoMirrored.Outlined.PlaylistPlay,
        filledIcon = Icons.AutoMirrored.Filled.PlaylistPlay,
        onClick = { },
        selected = false
    ),
    MyNavigationBarItems(
        label = "Folders",
        outlineIcon = Icons.Outlined.Folder,
        filledIcon = Icons.Filled.Folder,
        onClick = { },
        selected = false
    )
)