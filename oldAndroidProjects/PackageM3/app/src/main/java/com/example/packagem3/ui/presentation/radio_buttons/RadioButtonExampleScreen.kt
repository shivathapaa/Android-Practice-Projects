package com.example.packagem3.ui.presentation.radio_buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

@Composable
fun RadioButtonExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        MySimpleRadioButton()
        MyRadioButtonWithGroup()
    }
}

@Composable
fun MySimpleRadioButton(
    modifier: Modifier = Modifier
) {
    var state by remember { mutableStateOf(true) }

    Row(
        modifier = modifier.selectableGroup()
    ) {
        RadioButton(selected = state, onClick = { state = true })
        RadioButton(selected = !state, onClick = { state = false })
    }
}

@Composable
fun MyRadioButtonWithGroup(
    modifier: Modifier = Modifier
) {
    val radioOptions = listOf("Hi", "Hello", "Hola", "Namaste")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(
        modifier = modifier.selectableGroup()
    ) {
        radioOptions.forEach { text ->
            ListItem(
                modifier = Modifier
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton // for accessibility
                    ),
                headlineContent = { Text(text = text) },
                leadingContent = {
                    RadioButton(selected = text == selectedOption, onClick = null) // null recommended for accessibility with screenreaders
                }
            )
        }
    }
    
}