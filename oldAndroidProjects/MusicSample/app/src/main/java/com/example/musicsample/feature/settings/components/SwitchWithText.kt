package com.example.musicsample.feature.settings.components

import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SwitchWithText(
    title: String,
    description: String,
    checkedState: Boolean,
    onCheckedStateChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    ListItem(
        modifier = modifier.toggleable(
            value = checkedState,
            enabled = enabled,
            onValueChange = { onCheckedStateChanged(it) }),
        headlineContent = { Text(text = title) },
        supportingContent = {
            Text(text = description)
        },
        trailingContent = {
            Switch(checked = checkedState, onCheckedChange = null)
        }
    )
}