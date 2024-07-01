package com.shivathapaa.otpinput

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun OtpInputField(
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit
) {
    var otpValues = remember { mutableStateListOf(*Array(otpLength) { "" }) }
    val focusRequesters = List(otpLength) { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row {
        otpValues.forEachIndexed { index, value ->
            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        otpValues[index] = newValue
                        if (newValue.isNotEmpty()) {
                            if (index < otpLength - 1) {
                                focusRequesters[index + 1].requestFocus()
                            } else {
                                keyboardController?.hide()
                                onOtpComplete(otpValues.joinToString(""))
                            }
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (index == otpLength - 1) ImeAction.Done else ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (index < otpLength - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    },
                    onDone = {
                        keyboardController?.hide()
                        onOtpComplete(otpValues.joinToString(""))
                    }
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .focusRequester(focusRequesters[index])
                    .onFocusChanged {
                        if (it.isFocused && otpValues[index].isEmpty() && index > 0 && otpValues[index - 1].isEmpty()) {
                            focusRequesters[index - 1].requestFocus()
                        }
                    },
//                visualTransformation = VisualTransformation { text ->
//                    TransformedText(text, OffsetMapping.Identity)
//                },
                textStyle = MaterialTheme.typography.bodyMedium
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }
}