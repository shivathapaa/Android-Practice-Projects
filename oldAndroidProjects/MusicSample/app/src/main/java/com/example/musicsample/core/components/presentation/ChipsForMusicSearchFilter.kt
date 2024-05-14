package com.example.musicsample.core.components.presentation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.dimensionResource
import com.example.musicsample.R
import com.example.musicsample.core.components.data.FilterMusicChipCategory
import com.example.musicsample.core.components.data.listOfChipsCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipsForMusicSearchFilter(
    modifier: Modifier = Modifier,
    list: List<FilterMusicChipCategory> = listOfChipsCategory
) {
    var selectedCategory by remember { mutableIntStateOf(0) }

    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
        list.forEachIndexed { index, category ->
            FilterChip(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.tiny_padding)),
                selected = selectedCategory == index,
                onClick = {
                    selectedCategory = index
                    category.onCategoryClick
                },
                leadingIcon = { Icon(imageVector = category.icon, contentDescription = null) },
                label = {
                    Text(text = category.categoryLabel)
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiSelectionChipsForSearchFilter(
    modifier: Modifier = Modifier,
    list: List<FilterMusicChipCategory> = listOfChipsCategory
) {
    val selectedCategories = remember { mutableStateListOf<Int>() }

    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
        list.forEachIndexed { index, category ->
            FilterChip(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.tiny_padding)),
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
            )
        }
    }
}