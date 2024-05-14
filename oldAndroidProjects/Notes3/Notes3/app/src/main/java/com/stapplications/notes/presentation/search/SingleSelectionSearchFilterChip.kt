package com.stapplications.notes.presentation.search

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stapplications.notes.R


@Composable
fun MultiSelectionChipsForSearchFilter(
    modifier: Modifier = Modifier,
    list: List<ChipCategory> = listOfChipsCategory
) {
    val selectedCategories = remember { mutableStateListOf<Int>() }

    Row(
//        modifier = modifier.horizontalScroll(rememberScrollState()), // for extra categories
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
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
                    Icon(
                        painter = painterResource(id = category.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(id = category.categoryLabel))
                },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}


data class ChipCategory(
    val onCategoryClick: () -> Unit,
    @DrawableRes val icon: Int,
    @StringRes val categoryLabel: Int
)

val listOfChipsCategory = listOf<ChipCategory>(
    ChipCategory(
        onCategoryClick = {},
        icon = R.drawable.done_all,
        categoryLabel = R.string.all
    ),
    ChipCategory(
        onCategoryClick = {},
        icon = R.drawable.note,
        categoryLabel = R.string.notes
    ),
    ChipCategory(
        onCategoryClick = {},
        icon = R.drawable.check_list,
        categoryLabel = R.string.todos
    )
)