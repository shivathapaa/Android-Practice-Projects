package com.example.samplewithanimationsm1.core.presentation.components.searchbox

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.samplewithanimationsm1.core.presentation.components.listOfAnimNavBarItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleSelectionSearchFilterChip(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var selectedCategory by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier.horizontalScroll(scrollState)
    ) {
        listOfAnimNavBarItem.forEachIndexed { index, category ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = selectedCategory == index,
                onClick = { selectedCategory = index },
                label = { Text(text = category.label) },
                leadingIcon = { Icon(imageVector = category.filledIcon, contentDescription = null) }
            )
        }
    }

}