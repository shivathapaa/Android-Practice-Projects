package com.example.packagem3.core.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.PlaylistPlay
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Album
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.SupervisorAccount
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MySingleSelectionChipsForSearchFilter(
    modifier: Modifier = Modifier,
    list: List<CategoryChip> = listOfChipsCategory
) {
    var selectedCategory by remember { mutableIntStateOf(0) }

    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
        list.forEachIndexed { index, category ->
            FilterChip(
                selected = selectedCategory == index,
                onClick = {
                    selectedCategory = index
                    category.onCategoryClick
                },
                leadingIcon = { Icon(imageVector = category.icon, contentDescription = null) },
                label = {
                    Text(text = category.categoryLabel)
                },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun MyMultiSelectionChipsForSearchFilter(
    modifier: Modifier = Modifier,
    list: List<CategoryChip> = listOfChipsCategory
) {
    val selectedCategories = remember { mutableStateListOf<Int>() }

    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
        list.forEachIndexed { index, category ->
            FilterChip(
                selected = index in selectedCategories,
                onClick = {
                    if (index in selectedCategories) {
                        selectedCategories.remove(index)
                    } else {
                        selectedCategories.add(index)
                    }
                    category.onCategoryClick
                },
                leadingIcon = { Icon(imageVector = category.icon, contentDescription = null) },
                label = {
                    Text(text = category.categoryLabel)
                },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}


data class CategoryChip(
    val onCategoryClick: () -> Unit,
    val icon: ImageVector,
    val categoryLabel: String
)

val listOfChipsCategory = listOf<CategoryChip>(
    CategoryChip(
        onCategoryClick = {},
        icon = Icons.Rounded.DoneAll,
        categoryLabel = "All"
    ),
    CategoryChip(
        onCategoryClick = {},
        icon = Icons.Rounded.MusicNote,
        categoryLabel = "Songs"
    ),
    CategoryChip(
        onCategoryClick = {},
        icon = Icons.Rounded.Album,
        categoryLabel = "Albums"
    ),
    CategoryChip(
        onCategoryClick = {},
        icon = Icons.Rounded.AccountCircle,
        categoryLabel = "Albums"
    ),
    CategoryChip(
        onCategoryClick = {},
        icon = Icons.Rounded.SupervisorAccount,
        categoryLabel = "Album Artists"
    ),
    CategoryChip(
        onCategoryClick = {},
        icon = Icons.Rounded.MusicNote,
        categoryLabel = "Genre"
    ),
    CategoryChip(
        onCategoryClick = {},
        icon = Icons.AutoMirrored.Rounded.PlaylistPlay,
        categoryLabel = "Playlists"
    ),
)