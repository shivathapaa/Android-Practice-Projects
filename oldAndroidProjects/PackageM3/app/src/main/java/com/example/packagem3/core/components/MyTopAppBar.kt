package com.example.packagem3.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    modifier: Modifier = Modifier,
    onMoreClick: () -> Unit = {},
) {
    val scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    MediumTopAppBar(
        modifier = modifier,
        title = { Text(text = "Home") },
        actions = {
            IconButton(onClick = { onMoreClick() }) {
                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.Save, contentDescription = "Save")
            }
        },
        scrollBehavior = scrollBehavior
    )
}