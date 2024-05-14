package com.example.packagem3.ui.presentation.switches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun MySwitchesExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        MySimpleSwitch()
        MyThumbSwitch()
        MyDoubleThumbSwitch()
        MySwitchWithRow()
    }
}

@Composable
fun MySimpleSwitch(
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(true) }
    Switch(checked = checked, onCheckedChange = { checked = it })
}

@Composable
fun MyThumbSwitch(
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(true) }

    val icon: (@Composable () -> Unit)? =
        if (checked) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        } else {
            null
        }

    Switch(checked = checked, onCheckedChange = { checked = it }, thumbContent = icon)
}

@Composable
fun MyDoubleThumbSwitch(
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(true) }

    val icon: (@Composable () -> Unit) =
        if (checked) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        } else {
            {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        }

    Switch(checked = checked, onCheckedChange = { checked = it }, thumbContent = icon)
}

@Composable
fun MySwitchWithRow(
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .toggleable(
                value = checked,
                onValueChange = { checked = it },
                role = Role.Switch // for accessibility
            )
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Enable some feature")
        Switch(checked = checked, onCheckedChange = null)
    }
}