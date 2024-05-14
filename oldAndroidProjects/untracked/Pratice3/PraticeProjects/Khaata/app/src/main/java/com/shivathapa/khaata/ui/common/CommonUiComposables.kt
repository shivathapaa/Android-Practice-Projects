package com.shivathapa.khaata.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.shivathapa.khaata.R

@Composable
fun SurfaceText(
    text: String, modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clip(MaterialTheme.shapes.small)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@Composable
fun SurfaceComplimentText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.labelLarge,
) {
    Text(
        text = text, style = style, color = MaterialTheme.colorScheme.secondary, modifier = modifier
    )
}

@Composable
fun BottomNextCancelButtons(
    onSaveButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    saveEnabled: Boolean = true,
    cancelEnabled: Boolean = true,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ) {
        Button(
            onClick = onSaveButtonClicked, enabled = saveEnabled, modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(id = R.string.save))
        }
        OutlinedButton(
            onClick = onCancelButtonClicked, enabled = cancelEnabled, modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }
}


@Composable
fun EditableOutlineTextField(
    @StringRes label: Int,
    leadingIcon: @Composable (() -> Unit)?,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        leadingIcon = leadingIcon,
        keyboardOptions = keyboardOptions,
        label = { Text(stringResource(id = label)) },
        modifier = modifier
    )
}

@Composable
fun EditableNormalTextField(
    @StringRes label: Int,
//    @StringRes placeholder: Int,
    leadingIcon: ImageVector,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        label = { Text(text = stringResource(id = label)) },
//        placeholder = {
//            Text(text = stringResource(id = placeholder))
//        },
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = null)
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        colors = colors
    )
}