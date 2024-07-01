package com.shivathapaa.otpinput

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shivathapaa.otpinput.ui.theme.OtpInputTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtpInputTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OTPInputScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun OTPInputScreen(
    modifier: Modifier = Modifier,
    otpLength: Int = 5
) {
    val otpValues = remember { mutableStateListOf(*Array(otpLength) { "" }) }
    val focusRequesters = List(otpLength) { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    var showOtp by remember { mutableStateOf(false) } /* Not good practice, validate from viewModel */

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = showOtp) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "OTP string: ${otpValues.joinToString("")}",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Text(text = "Enter OTP", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            otpValues.forEachIndexed { index, value ->
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f) /* Keep as you wish ;) */ /* On second thought, you can use .size(52.dp) or 46.dp is perfect 😁 */
                        .padding(6.dp)
                        .focusRequester(focusRequesters[index])
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Backspace) {
                                if (otpValues[index].isEmpty() && index > 0) {
                                    otpValues[index] = ""
                                    focusRequesters[index - 1].requestFocus()
                                } else {
                                    otpValues[index] = ""
                                }
                                true
                            } else {
                                false
                            }
                        },
                    value = value,
                    onValueChange = { newValue ->
                        showOtp = false  /* Not good practice */
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
                            onOtpComplete(otpValues.joinToString("")) /* Not good practice, do it from viewModel */
                        }
                    ),
                    isError = otpValues.contains("."), /* Not good practice, validate from viewModel */
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            enabled = otpValues.joinToString("")
                .replace(" ", "").length == otpLength &&
                    !otpValues.contains("."), /* Not good practice, validate from viewModel */
            onClick = {
                onOtpComplete(otpValues.joinToString("")) /* Not good practice, do it from viewModel */
                showOtp = true
            }
        ) {
            Text(text = "Submit")
        }
    }

    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }
}

private fun onOtpComplete(otp: String) {
    Log.d("OtpStringValue", "OTP string: -$otp-")
}