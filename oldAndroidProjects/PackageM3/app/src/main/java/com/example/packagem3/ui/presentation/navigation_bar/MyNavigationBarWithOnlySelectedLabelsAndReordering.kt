package com.example.packagem3.ui.presentation.navigation_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.SettingsSuggest
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.packagem3.R

@Composable
fun MyNavigationBarWithOnlySelectedLabelsAndReordering(
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = itemListOfMyNavigationBar

    var openDialog by remember { mutableStateOf(false) }
    val onOpenDialogStateChange: (Boolean) -> Unit = { openDialog = it }

    Column(
        modifier = modifier
    ) {
        NavigationBar {
            items.take(5).forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        item.onClick
                    },
                    icon = {
                        Icon(
                            imageVector = item.filledIcon,
                            contentDescription = item.label
                        )
                    },
                    label = { Text(text = item.label) },
                    alwaysShowLabel = false
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))

        FilledTonalButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { openDialog = true }
        ) {
            Text(text = "Reorder Category")
        }
        if (openDialog) {
            ReorderLibraryCategories(
                onOpenDialogStateChange = onOpenDialogStateChange
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReorderLibraryCategories(
    onOpenDialogStateChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var showReorderDialog by remember { mutableStateOf(true) }

    val changeDialogState: (Boolean) -> Unit = {
        showReorderDialog = false
        onOpenDialogStateChange(false)
    }


    if (showReorderDialog) {
        BasicAlertDialog(
            onDismissRequest = {
                showReorderDialog = false
                onOpenDialogStateChange(false)
            },
            modifier = modifier
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = AlertDialogDefaults.shape,
                color = AlertDialogDefaults.containerColor,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                LibraryCategoryDraggableRows(showReorderDialog = changeDialogState)
            }
        }
    }
}

// TODO: Implement Draggable functionality

@Composable
fun LibraryCategoryDraggableRows(
    showReorderDialog: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val categoryList = itemListOfMyNavigationBar
    Column(
        modifier = modifier
    ) {
        ReorderAlertDialogTitle(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(dimensionResource(id = R.dimen.large_padding))
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.medium_padding)))

        categoryList.forEachIndexed { index, category ->
            val (checkedState, onCheckedStateChange) = remember { mutableStateOf(category.selected) }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .toggleable(
                        value = checkedState,
                        onValueChange = { onCheckedStateChange(!checkedState) }
                    )
                    .padding(horizontal = dimensionResource(id = R.dimen.large_padding)),
            ) {
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = null,
                    modifier = Modifier
//                        onCheckedChange = { onCheckedStateChange(!checkedState) }
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.medium_padding)))
//                Icon(
//                    imageVector = category.filledIcon,
//                    contentDescription = null,
//                    tint = AlertDialogDefaults.iconContentColor
//                )
//                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.extra_small_padding)))
                Text(
                    text = category.label,
                    color = AlertDialogDefaults.textContentColor,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        imageVector = Icons.Filled.DragHandle,
                        contentDescription = "Drag Handle",
                        tint = AlertDialogDefaults.iconContentColor
                    )
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.medium_padding)))

        ReorderAlertDialogButtons(
            showReorderDialog = showReorderDialog,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(dimensionResource(id = R.dimen.large_padding))
        )
    }
}


@Composable
fun ReorderAlertDialogTitle(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.SettingsSuggest,
            contentDescription = null,
            tint = AlertDialogDefaults.iconContentColor,
            modifier = Modifier.size(dimensionResource(id = R.dimen.large_padding))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
        Text(
            text = "Manage your preference",
            style = MaterialTheme.typography.titleLarge,
            color = AlertDialogDefaults.titleContentColor,
            modifier = Modifier
        )
    }
}

@Composable
fun ReorderAlertDialogButtons(
    showReorderDialog: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        OutlinedButton(
            onClick = {
                showReorderDialog(false)
            },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Cancel")
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.medium_padding)))
        Button(
            onClick = {
                showReorderDialog(false)
            },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun ReorderAlertDialogTextButtons(
    showReorderDialog: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TextButton(
            onClick = {
                showReorderDialog(false)
            },
            modifier = Modifier
        ) {
            Text(text = "Dismiss")
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.extra_small_padding)))
        TextButton(
            onClick = {
                showReorderDialog(false)
            },
            modifier = Modifier
        ) {
            Text(text = "Save")
        }
    }
}


// Ignored because of more space between ListItem composables


@Composable
fun LibraryCategoryList(
    showReorderDialog: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val categoryList = itemListOfMyNavigationBar

        ReorderAlertDialogTitle(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(dimensionResource(id = R.dimen.large_padding))
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.medium_padding)))

        categoryList.forEachIndexed { index, category ->
            val (checkedState, onCheckedStateChange) = remember { mutableStateOf(true) }

            ListItem(
                modifier = Modifier
                    .toggleable(
                        value = checkedState,
                        onValueChange = { onCheckedStateChange(!checkedState) }
                    )
                    .padding(horizontal = dimensionResource(id = R.dimen.extra_small_padding)),
                headlineContent = {
                    Row {
                        Icon(
                            imageVector = category.filledIcon,
                            contentDescription = null,
                            tint = AlertDialogDefaults.iconContentColor
                        )
                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
                        Text(
                            text = category.label,
                            color = AlertDialogDefaults.textContentColor
                        )
                    }
                },
                leadingContent = {
                    Checkbox(
                        checked = checkedState,
                        onCheckedChange = null,
                        modifier = Modifier
//                        onCheckedChange = { onCheckedStateChange(!checkedState) }
                    )
                },
                trailingContent = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.DragHandle,
                            contentDescription = "Drag Handle",
                            tint = AlertDialogDefaults.iconContentColor
                        )
                    }
                }
            )
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.medium_padding)))
        ReorderAlertDialogButtons(
            showReorderDialog = showReorderDialog,
            modifier = Modifier
                .align(Alignment.End)
                .padding(dimensionResource(id = R.dimen.large_padding))
        )
    }
}