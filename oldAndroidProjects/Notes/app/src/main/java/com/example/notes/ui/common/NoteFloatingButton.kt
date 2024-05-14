package com.example.notes.ui.common

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun NoteFloatingActionButton(
    onClick: () -> Unit,
    icon: Painter,
    text: String,
    functionDescription: String,
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        expanded = expanded,
        icon = { Icon(painter = icon, contentDescription = functionDescription) },
        text = { Text(text = text) },
        modifier = modifier
    )

}