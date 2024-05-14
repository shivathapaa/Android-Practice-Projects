package com.example.packagem3.ui.presentation.checkbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import com.example.packagem3.R

@Composable
fun CheckboxScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        SingleCheckBox()
        MultiStateCheckBox()
    }
}

@Composable
fun SingleCheckBox(
    modifier: Modifier = Modifier
) {
    val (checkedState, onStateChange) = remember { mutableStateOf(true) }
    MyCheckboxRow(
        checkedState = checkedState,
        onStateChange = onStateChange
    )
}

@Composable
fun MultiStateCheckBox(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.medium_padding))
    ) {
        val (state, onStateChange) = remember { mutableStateOf(true) }
        val (state2, onStateChange2) = remember { mutableStateOf(true) }
        val (state3, onStateChange3) = remember { mutableStateOf(true) }

        val parentState = remember(state, state2, state3) {
            if (state && state2 && state3) ToggleableState.On
            else if (!state && !state2 && !state3) ToggleableState.Off
            else ToggleableState.Indeterminate
        }

        val onParentClick = {
            val boxState = parentState != ToggleableState.On
            onStateChange(boxState)
            onStateChange2(boxState)
            onStateChange3(boxState)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .triStateToggleable(
                    state = parentState,
                    onClick = onParentClick,
                )
        ) {
            TriStateCheckbox(
                state = parentState,
                onClick = onParentClick
            )
            Text(
                text = "Choose to select",
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.medium_padding)),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
        Column(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.large_padding)
            )
        ) {
            MyCheckboxRow(checkedState = state, onStateChange = onStateChange)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
            MyCheckboxRow(checkedState = state2, onStateChange = onStateChange2)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
            MyCheckboxRow(checkedState = state3, onStateChange = onStateChange3)
        }

    }
}

@Composable
fun MyCheckboxRow(
    checkedState: Boolean,
    onStateChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    checkboxText: String = "Select checkbox"
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .toggleable(
                value = checkedState,
                onValueChange = { onStateChange(!checkedState) },
                role = Role.Checkbox
            )
            .padding(dimensionResource(id = R.dimen.medium_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = null
        )
        Text(
            text = checkboxText,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.medium_padding))
        )
    }
}