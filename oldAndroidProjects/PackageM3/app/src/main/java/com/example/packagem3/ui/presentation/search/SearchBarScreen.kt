package com.example.packagem3.ui.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.packagem3.R
import com.example.packagem3.ui.presentation.search.chips.ChipsWithLeadingIcon

@Composable
fun SearchBarScreen(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit = {},
) {
//    Scaffold(
//        modifier = modifier
//    ) { paddingValues ->
//        MySearchBar(
//            onMenuClick = onMenuClick,
//            modifier = Modifier
//                .padding(paddingValues)
//        )
//    }
    MySearchBar(
        onMenuClick = onMenuClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit = {},
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }

    var active by rememberSaveable {
        mutableStateOf(false)
    }

    val items = remember {
        mutableStateListOf("Android", "iPhone", "Macbook", "Windows", "Hello world!")
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter),
            query = query,
            onQueryChange = { query = it },
            onSearch = {
//                active = false
                keyboardController?.hide()
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = {
                Text(text = stringResource(R.string.search_your_music))
            },
            leadingIcon = {
                if (active) {
                    IconButton(
                        onClick = {
                            query = ""
                            active = false
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                } else {
                    IconButton(onClick = { onMenuClick() }) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = stringResource(R.string.menu)
                        )
                    }
                }
            },
            trailingIcon = {
                if (active) {
                    IconButton(onClick = {
                        if (query.isNotEmpty()) {
                            query = ""
                        } else {
                            active = false
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = stringResource(R.string.close)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.AccountCircle,
                            contentDescription = stringResource(R.string.profile)
                        )
                    }
                }
            }
        ) {
            ChipsWithLeadingIcon(
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.extra_small_padding))
            )
            items.forEach {
                ListItem(
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.history),
                            contentDescription = null
                        )
                    },
                    headlineContent = { Text(text = it) }
                )
            }
        }
        FakeListItems(
            paddingValues = PaddingValues(
                start = dimensionResource(id = R.dimen.medium_padding),
                end = dimensionResource(id = R.dimen.medium_padding),
                top = dimensionResource(id = R.dimen.after_search_box_padding),
                bottom = dimensionResource(id = R.dimen.medium_padding),
            ),
            modifier = Modifier,
        )
    }
}

@Composable
fun FakeListItems(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_padding)),
        modifier = modifier
    ) {
        val list = List(100) { "Let's count $it" }
        items(count = list.size) {
            Text(
                list[it],
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.medium_padding))
            )
        }
    }
}