package com.example.packagem3.ui.presentation.search.chips

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.PlaylistPlay
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Album
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.SupervisorAccount
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ChipsForSearchFilter(
    modifier: Modifier = Modifier
) {
//    val listOfFilterCategory: List<String> = listOf("All", "Songs", "Albums", "Artist", "Album Artist", "Genre", "Playlist")

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        ChipsWithLeadingIcon()
        ChipsWithoutLeadingIcon()
    }


}

@Composable
fun ChipsWithLeadingIcon(
    modifier: Modifier = Modifier,
    list: List<CategoryChips> = listOfChipsCategory,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipsWithoutLeadingIcon(
    modifier: Modifier = Modifier,
    list: List<CategoryChips> = listOfChipsCategory,
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        horizontalArrangement = Arrangement.Start
    ) {
        val selectedCategories = remember { mutableStateListOf<Int>() }

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
                leadingIcon = {
                    if (index in selectedCategories) {
                        Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
                    } else {
                        Icon(imageVector = category.icon, contentDescription = null)
                    }
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable { }
                    )
                },
                label = {
                    Text(text = category.categoryLabel)
                },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

data class CategoryChips(
    val onCategoryClick: () -> Unit,
    val icon: ImageVector,
    val categoryLabel: String
)

val listOfChipsCategory = listOf<CategoryChips>(
    CategoryChips(
        onCategoryClick = {},
        icon = Icons.Rounded.DoneAll,
        categoryLabel = "All"
    ),
    CategoryChips(
        onCategoryClick = {},
        icon = Icons.Rounded.MusicNote,
        categoryLabel = "Songs"
    ),
    CategoryChips(
        onCategoryClick = {},
        icon = Icons.Rounded.Album,
        categoryLabel = "Albums"
    ),
    CategoryChips(
        onCategoryClick = {},
        icon = Icons.Rounded.AccountCircle,
        categoryLabel = "Albums"
    ),
    CategoryChips(
        onCategoryClick = {},
        icon = Icons.Rounded.SupervisorAccount,
        categoryLabel = "Album Artists"
    ),
    CategoryChips(
        onCategoryClick = {},
        icon = Icons.Rounded.MusicNote,
        categoryLabel = "Genre"
    ),
    CategoryChips(
        onCategoryClick = {},
        icon = Icons.AutoMirrored.Rounded.PlaylistPlay,
        categoryLabel = "Playlists"
    ),
)