package com.example.packagem3.ui.presentation.segmented_button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoMode
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.packagem3.R

@Composable
fun SegmentedButtonScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        SingleChoiceSegmentedButton()
        SingleChoiceThemeSegmentedButton()
        MultiChoiceSegmentedButton()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleChoiceSegmentedButton(
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf<String>("Day", "Month", "Year")

    SingleChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        options.forEachIndexed { index: Int, optionLabel: String ->
            SegmentedButton(
                selected = index == selectedIndex,
                onClick = { selectedIndex = index },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
            ) {
                Text(text = optionLabel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleChoiceThemeSegmentedButton(
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf<Pair<String, ImageVector>>(
        "Light" to Icons.Default.LightMode,
        "Dark" to Icons.Default.DarkMode,
        "System" to Icons.Default.AutoMode
    )

    SingleChoiceSegmentedButtonRow(
        space = SegmentedButtonDefaults.BorderWidth,
        modifier = modifier
            .clip(RoundedCornerShape(100))
            .background(MaterialTheme.colorScheme.onSurface)
    ) {
        options.forEachIndexed { index: Int, option: Pair<String, ImageVector> ->
            SegmentedButton(
                selected = index == selectedIndex,
                onClick = { selectedIndex = index },
                shape = if (index == selectedIndex) RoundedCornerShape(100) else SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                icon = { Icon(imageVector = option.second, contentDescription = null) },
                border = SegmentedButtonDefaults.borderStroke(
                    color = Color.Transparent,
                    width = 0.dp
                ),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = MaterialTheme.colorScheme.surface,
                    activeContentColor = MaterialTheme.colorScheme.onSurface,
                    inactiveContainerColor = MaterialTheme.colorScheme.onSurface,
                    inactiveContentColor = MaterialTheme.colorScheme.surface,
                ),
                modifier = Modifier
                    .padding(
                        start = if (options.first() == option) dimensionResource(id = R.dimen.segmented_button_start_end_padding) else 0.dp,
                        end = if (options.last() == option) dimensionResource(id = R.dimen.segmented_button_start_end_padding) else 0.dp,
                    )
            ) {
                Text(text = option.first)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiChoiceSegmentedButton(
    modifier: Modifier = Modifier
) {
    val checkedList = remember { mutableStateListOf<Int>() }
    val options = optionsForSegmentedButtons

    MultiChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        options.forEachIndexed { index, labelWithIcon ->
            SegmentedButton(
                checked = index in checkedList,
                onCheckedChange = {
                    if (index in checkedList) {
                        checkedList.remove(index)
                    } else {
                        checkedList.add(index)
                    }
                },
                icon = {
                    SegmentedButtonDefaults.Icon(active = index in checkedList) {
                        Icon(
                            imageVector = labelWithIcon.icon,
                            contentDescription = null,
                            modifier = Modifier.size(SegmentedButtonDefaults.IconSize) // for even size
                        )
                    }
                },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
            ) {
                Text(text = labelWithIcon.label)
            }
        }
    }
}

data class Options(
    val label: String,
    val icon: ImageVector
)

private val optionsForSegmentedButtons: List<Options> = listOf(
    Options(label = "Favourites", icon = Icons.Rounded.FavoriteBorder),
    Options(label = "Trending", icon = Icons.Rounded.Notifications),
    Options(label = "Saved", icon = Icons.Rounded.Add)
)
