package com.stapplications.notes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stapplications.notes.navigation.NotesNavHost

@Composable
fun NotesApp(
    modifier: Modifier = Modifier
) {
    NotesNavHost(modifier = modifier)
}