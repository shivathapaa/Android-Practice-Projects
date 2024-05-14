package com.example.zplayer.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zplayer.ui.theme.ZplayerTheme

@Composable
fun ItemListScreen(
    modifier: Modifier = Modifier
) {
    val listOfMusics = fakeList
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(
            items = listOfMusics
        ) {musicDetails ->
            ListDetails(
                heading = musicDetails.heading,
                supportingText = musicDetails.supportingText,
                leadingContent = musicDetails.image)
        }
    }
}

@Composable
fun ListDetails(
    heading: String,
    supportingText: String,
    leadingContent: Int,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = {
            Text(text = heading)
        },
        supportingContent = {
            Text(text = supportingText)
        },
        modifier = Modifier,
        leadingContent = {
            Image(
                painter = painterResource(id = leadingContent),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        },
        trailingContent = {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        },
        tonalElevation = ListItemDefaults.Elevation,
        shadowElevation = ListItemDefaults.Elevation,
    )
}

@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    ZplayerTheme {
        ItemListScreen()
    }
}