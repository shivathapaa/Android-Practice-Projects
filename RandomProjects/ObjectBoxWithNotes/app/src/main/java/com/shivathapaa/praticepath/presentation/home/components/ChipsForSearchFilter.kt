package com.shivathapaa.praticepath.presentation.home.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.shivathapaa.praticepath.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipsForSearchFilter(
    modifier: Modifier = Modifier,
    list: List<String> = listOfChipsCategory
) {
    var selectedCategory by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center
    ) {
        list.forEachIndexed { index, category ->
            FilterChip(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.very_small_padding)),
                selected = selectedCategory == index,
                onClick = {
                    selectedCategory = index
                },
                label = {
                    Text(text = category)
                },
            )
        }
    }
}

val listOfChipsCategory: List<String> = listOf("By date", "By title", "By sub-title")