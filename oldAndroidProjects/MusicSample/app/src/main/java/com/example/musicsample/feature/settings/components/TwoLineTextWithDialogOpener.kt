package com.example.musicsample.feature.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TwoLineTextWithDialogOpener(
    title: String,
    description: String,
    onOpenDialogClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .clickable(onClick = onOpenDialogClick),
        headlineContent = { Text(text = title) },
        supportingContent = {
            Text(text = description)
        }
    )
}
