package com.stapplications.notes.presentation.screens.note.presentation.update.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.stapplications.notes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteUpsertTopAppBar(
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit,
    onCancelClick: () -> Unit,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit = {},
    showDeleteIcon: Boolean,
    title: @Composable () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = {
            IconButton(onClick = onUpClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        actions = {
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = stringResource(R.string.share)
                )
            }
            if (showDeleteIcon) {
                IconButton(onClick = onDeleteClick) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete")
                }
            }
            IconButton(onClick = onCancelClick) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "Cancel")
            }
        }
    )
}