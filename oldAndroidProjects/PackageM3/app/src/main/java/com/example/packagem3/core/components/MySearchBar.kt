package com.example.packagem3.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit = {}
) {
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    SearchBar(
        modifier = modifier,
        query = query,
        onQueryChange = { query = it },
        onSearch = {
            coroutineScope.launch { keyboardController?.hide() }
            // perform search action here
        },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text(text = "Search your music") },
        leadingIcon = {
            if (active) {
                IconButton(onClick = {
                    query = ""
                    active = false
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            } else {
                IconButton(onClick = { onMenuClick() }) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Open navigation drawer"
                    )
                }
            }
        },
        trailingIcon = {
            var closeDescription: String = "Close"
            if (active) {
                IconButton(onClick = {
                    if (query.isNotEmpty()) {
                        query = ""
                        closeDescription = "Clear"
                    } else {
                        active = false
                        closeDescription = "Close"
                    }
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = closeDescription
                    )
                }
            } else {
                IconButton(onClick = { onProfileClick() }) {
                    Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "Profile")
                }
            }
        }
    ) {
        MySingleSelectionChipsForSearchFilter()
    }
}