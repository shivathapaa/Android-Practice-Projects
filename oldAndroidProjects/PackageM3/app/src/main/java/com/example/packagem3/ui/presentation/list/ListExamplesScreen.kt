package com.example.packagem3.ui.presentation.list

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.packagem3.R

@Composable
fun ListExamplesScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = dimensionResource(id = R.dimen.medium_padding))
    ) {
        HorizontalDivider()
        SingleLineList()
        TwoLineList()
        ThreeLineList()
        ListWithButtonSwitchCheckboxHandle()
        ListWithSwipingExpandOrCollapsingDragging(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        ListWithIconAvatarImageVideo()
    }
}

@Composable
fun SingleLineList(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ListItem(
            headlineContent = { Text(text = "This is single line list.") },
            leadingContent = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            },
//            overlineContent = {},
//            supportingContent = {},
//            trailingContent = { },
        )
        HorizontalDivider()
    }
}

@Composable
fun TwoLineList(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ListItem(
            headlineContent = { Text(text = "This is two line list.") },
            leadingContent = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            },
//            overlineContent = {},
            supportingContent = { Text(text = "This is the supporting text.") },
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            },
        )
        HorizontalDivider()
    }
}

@Composable
fun ThreeLineList(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ListItem(
            headlineContent = { Text(text = "This is single line list.") },
            leadingContent = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            },
            overlineContent = { Text(text = "Overline") },
            supportingContent = { Text("Secondary text that is long and perhaps goes onto another line") },
            trailingContent = { Text(text = "4:35") },
        )
        HorizontalDivider()
    }
}

@Composable
fun ListWithButtonSwitchCheckboxHandle(
    modifier: Modifier = Modifier
) {
    val (checkedState, onCheckboxStateChange) = remember { mutableStateOf(true) }
    val (switchedState, onSwitchStateChange) = remember { mutableStateOf(true) }


    Column(modifier = modifier) {
        ListItem(
            headlineContent = {
                Text(
                    text = "Title Large.",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            leadingContent = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            },
//            overlineContent = {},
            supportingContent = { Text(text = "In three line list, trailing content isn't in middle while in two line list trailing content auto is centered.") },
            trailingContent = {
                Switch(
                    checked = switchedState,
                    onCheckedChange = { onSwitchStateChange(!switchedState) }
                )
            },
        )
        HorizontalDivider()
        ListItem(
            headlineContent = {
                Text(
                    text = "Title Large.",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            leadingContent = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            },
//            overlineContent = {},
            supportingContent = { Text(text = "Supporting content.") },
            trailingContent = {
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = { onCheckboxStateChange(!checkedState) })
            },
        )
        HorizontalDivider()
    }
}

@Composable
fun ListWithIconAvatarImageVideo(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ListItem(
            headlineContent = {
                Text(
                    text = "Title Medium",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            leadingContent = {
                Image(
                    painter = painterResource(id = R.drawable.cow_farm), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                )
            },
//            overlineContent = {},
            supportingContent = { Text(text = "Something supportive.") },
            trailingContent = { Text(text = "11:34:34") },
        )
        HorizontalDivider()
        ListItem(
            headlineContent = {
                Text(
                    text = "Title Medium",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            leadingContent = {
                Image(
                    painter = painterResource(id = R.drawable.cow_farm), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.medium_padding)))
                )
            },
//            overlineContent = {},
            supportingContent = { Text(text = "Something supportive.") },
            trailingContent = { Text(text = "4:34") },
        )
        HorizontalDivider()
        ListItem(
            headlineContent = {
                Text(
                    text = "Title Medium",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            leadingContent = {
                Image(
                    painter = painterResource(id = R.drawable.cow_farm), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.medium_padding)))
                )
            },
//            overlineContent = {},
            supportingContent = { Text(text = "Something supportive.") },
            trailingContent = { Text(text = "4:34") },
        )
        HorizontalDivider()
        ListItem(
            headlineContent = {
                Text(
                    text = "Title Medium",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            leadingContent = {
                Image(
                    painter = painterResource(id = R.drawable.cow_farm), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.medium_padding)))
                )
            },
//            overlineContent = {},
            supportingContent = { Text(text = "Something supportive. Let's make it multi line supportive text.") },
            trailingContent = { Text(text = "4:34") },
        )
        HorizontalDivider()
        ListItem(
            headlineContent = {
                Text(
                    text = "Title Medium",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            leadingContent = {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.cow_farm),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                    )
                    Image(
                        painter = painterResource(id = R.drawable.cow_farm),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
                    )
                }
            },
            overlineContent = { Text(text = "OVERLINE") },
            supportingContent = { Text(text = "This is the supporting text.") },
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            },
        )
        HorizontalDivider()
    }
}

@Composable
fun ListWithSwipingExpandOrCollapsingDragging(
    modifier: Modifier = Modifier
) {
    val showDropDownItems = remember { mutableStateOf(false) }

    Column(modifier = modifier.animateContentSize()) {
        ListItem(
            headlineContent = { Text(text = "This is drop down practice.") },
            leadingContent = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            },
//            overlineContent = {},
            supportingContent = {
                if (showDropDownItems.value) {
                    Column {
                        ListItem(
                            headlineContent = { Text(text = "Expanded listItem 1") },
                            supportingContent = { Text(text = "Supporting text of expanded item.") }
                        )
                        ListItem(
                            headlineContent = { Text(text = "Expanded listItem 2") }
                        )
                        Text(
                            text = "Simple text 1",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
                        Text(
                            text = "Simple text 2",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
                        Text(
                            text = "Simple text 2",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            },
            trailingContent = {
                if (!showDropDownItems.value) {
                    IconButton(onClick = { showDropDownItems.value = true }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "More"
                        )
                    }
                } else {
                    IconButton(onClick = { showDropDownItems.value = false }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "More"
                        )
                    }
                }
            },
        )
        HorizontalDivider()
    }
}