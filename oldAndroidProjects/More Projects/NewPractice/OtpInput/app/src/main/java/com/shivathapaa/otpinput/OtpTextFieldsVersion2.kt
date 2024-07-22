package com.shivathapaa.otpinput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly

/**
 * [OTPInputTextFields] is a custom OTP input fields for the OTP verification.
 * In this composable function, [onOtpInputComplete] callback is invoked when all OTP input fields are filled,
 * or when user presses the [ImeAction.Done] ime button. (If you are controlling it (i.e., invoking validations and navigation)
 * from other [Composable] like [Button] then you can simply pass empty lambda `{}` as callback).
 *
 *  See end of this file for `example of usage`.
 *
 * @param otpLength length of OTP code (this determines the number of [OutlinedTextField] for OTP code input)
 * @param otpValues list of [String] OTP values entered by the user. The length of this list is determined by otpLength.
 * Pass this `List(otpLength) { "" }` as default for future reference (To use, it should be passed from
 * the viewModel for value update).
 * @param onUpdateOtpValuesByIndex callback is invoked when OTP value is updated by the user. For each respective
 * input field it returns `index` of the list item where it is updating, and `value` of the OTP for that specific index.
 * @param onOtpInputComplete callback to be invoked when OTP input is complete. (This is where we validate the
 * OTP code, navigate to next screen, or call any function as per requirement) - pass all these as argument.
 * In the below function [OTPInputTextFields], it is invoked when all OTP input fields are filled (till last),
 * or when user presses the [ImeAction.Done] ime button.
 * @param isError is for dynamic feedback to User for OTP input after validation. Changes the color of text fields as error red.
 * Ignore this if not needed (i.e., default to `false`).
 *
 * - Just for more understanding and reference, `List(otpLength) { "" }` creates a list of `otpLength` size i.e, if `otpLength` is 4, then it will create
 * list of size 4 with all elements as empty string, which we will update using [onUpdateOtpValuesByIndex] callback.
 */
@Composable
fun OTPInputTextFields(
    otpLength: Int,
    onUpdateOtpValuesByIndex: (Int, String) -> Unit,
    onOtpInputComplete: () -> Unit,
    modifier: Modifier = Modifier,
    otpValues: List<String> = List(otpLength) { "" }, // Pass this as default for future reference
    isError: Boolean = false,
) {
    val focusRequesters = List(otpLength) { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
//        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
    ) {
        otpValues.forEachIndexed { index, value ->
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
//                    .width(64.dp)
                    .padding(6.dp)
                    .focusRequester(focusRequesters[index])
                    .onKeyEvent { keyEvent ->
                        if (keyEvent.key == Key.Backspace) {
                            if (otpValues[index].isEmpty() && index > 0) {
                                onUpdateOtpValuesByIndex(index, "")
                                focusRequesters[index - 1].requestFocus()
                            } else {
                                onUpdateOtpValuesByIndex(index, "")
                            }
                            true
                        } else {
                            false
                        }
                    },
                value = value,
                onValueChange = { newValue ->
                    // To use OTP code copied from keyboard
                    if (newValue.length == otpLength) {
                        for (i in otpValues.indices) {
                            onUpdateOtpValuesByIndex(
                                i,
                                if (i < newValue.length && newValue[i].isDigit()) newValue[i].toString() else ""
                            )
                        }
                        // You can/should transfer this logic in viewModel for validation and control function callback from length
                        val finalOtpValues = otpValues.filter { it.isDigitsOnly() }
                        if (otpValues.all { it.isNotEmpty() && it.isDigitsOnly() } && finalOtpValues.size == otpLength) {
                            keyboardController?.hide()
                            onOtpInputComplete()
                        }
                    } else if (newValue.length <= 1) {
                        onUpdateOtpValuesByIndex(index, newValue)
                        if (newValue.isNotEmpty()) {
                            if (index < otpLength - 1) {
                                focusRequesters[index + 1].requestFocus()
                            } else {
                                keyboardController?.hide()
                                focusManager.clearFocus()
                                onOtpInputComplete()
                            }
                        }
                    } else {
                        if (index < otpLength - 1) focusRequesters[index + 1].requestFocus()
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
                        focusManager.clearFocus()
                        onOtpInputComplete()
                    }
                ),
                shape = MaterialTheme.shapes.small,
                isError = isError,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }
}


// Reference for using OTP input fields

/*
// In Screen, inside Composable function,

OTPInputTextFields(
    otpLength = signUpUiState.otpLength,
    otpValues = signUpUiState.otpValues,
    isError = signUpUiState.isOtpError,
    onUpdateOtpValuesByIndex = { index, value ->
        screenModel.updateOtpValue(index, value)
    },
    onOtpInputComplete = {

        // Check for the otp validation
        if (screenModel.isOtpInputValuesAreValid()) {
            showLoadingDialog = true

            // Call the API for OTP verification and perform any necessary actions

            coroutineScope.launch {
                if ( /*If API response is success*/ ) {
                    showLoadingDialog = false

                    showSuccessDialogWithSuccessMessage = true

                    // After success, navigate to next screen

                } else {
                    showErrorMessage() // Response with error message
                }
            }
        }
    }
)


// In ViewModel,

data class SignUpUiState(
    val otpLength: Int = 6,
    val isOtpError: Boolean = false,
    val otpValues: List<String> = List(otpLength) { "" }
}


fun updateOtpValue(index: Int, value: String) {
    val newOtpValues = _signUpUiState.value.otpValues.toMutableList() // Making list mutable to update value

    newOtpValues[index] = value // Update value at the specified index

    _signUpUiState.update { currentState ->
        currentState.copy(
            otpValues = newOtpValues,
            isOtpError = false
        )
    }
}
*/
