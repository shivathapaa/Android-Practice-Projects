package com.example.packagem3.ui.presentation.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.packagem3.R

// USE BOTTOM SHEET IF POSSIBLE

@Composable
fun MenuExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.medium_padding))
    ) {
        MySimpleMenu(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
        MySimpleMenuWithListItem()
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
        MyReadOnlyDropDownMenuWithTextField(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
        MyEditableDropDownMenuWithTextField(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun MySimpleMenu(
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More"
        )
    }
    DropdownMenu(
        expanded = expanded, onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text(text = "Equalizer") },
            onClick = { },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Equalizer,
                    contentDescription = null
                )
            },
        )
        DropdownMenuItem(
            text = { Text(text = "Settings") },
            onClick = { },
            leadingIcon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) },
        )
        DropdownMenuItem(
            text = { Text(text = "Tutorial") },
            onClick = { },
            leadingIcon = { Icon(imageVector = Icons.Default.Info, contentDescription = null) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null
                )
            }
        )
        HorizontalDivider()
        DropdownMenuItem(
            text = { Text(text = "Delete") },
            onClick = { },
            leadingIcon = { Icon(imageVector = Icons.Default.Delete, contentDescription = null) },
            trailingIcon = { Text(text = "Del") }
        )
        DropdownMenuItem(
            text = { Text(text = "Remove") },
            onClick = { },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.RemoveCircle,
                    contentDescription = null
                )
            },
            trailingIcon = { Icon(imageVector = Icons.Default.Remove, contentDescription = null) }
        )
    }
}

@Composable
fun MySimpleMenuWithListItem(
    modifier: Modifier = Modifier
) {
    HorizontalDivider()
    ListItem(
        headlineContent = { Text(text = "This is single line list.") },
        leadingContent = {
            Image(
                painter = painterResource(id = R.drawable.cow_farm), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.medium_padding)))
            )
        },
        supportingContent = { Text("Secondary text") },
        trailingContent = { MySimpleMenu() },
        modifier = modifier
    )
    HorizontalDivider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyReadOnlyDropDownMenuWithTextField(
    modifier: Modifier = Modifier
) {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text(text = "Select option") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { selectedOption ->
                DropdownMenuItem(
                    text = { Text(text = selectedOption) },
                    onClick = {
                        selectedOptionText = selectedOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyEditableDropDownMenuWithTextField(
    modifier: Modifier = Modifier
) {
    val options = listOf<String>("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.menuAnchor(),
            value = selectedOptionText,
            onValueChange = { selectedOptionText = it },
            label = { Text(text = "Select or write") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )
        // filter options based on text field value
        val filteringOptions = options.filter { it.contains(selectedOptionText, ignoreCase = true) }

        if (filteringOptions.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                filteringOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }

}