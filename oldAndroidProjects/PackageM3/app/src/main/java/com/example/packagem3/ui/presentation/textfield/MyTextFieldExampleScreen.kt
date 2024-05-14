package com.example.packagem3.ui.presentation.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MyTextFieldExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        MySimpleTf()
        MySimpleOutlineTf()
        MyOutlineTfWithIcons()
        MyOutlineTfWithPreSuffix()
        MyOutlineTfWithSupportingText()
        MyOutlineTfWithKeyboardOption()
        MyOutlineTfWithPasswordInput()
        MyOutlineTfWithErrorSuccessState()
        MyOutlineTfWithTextArea()
    }
}

@Composable
fun MySimpleTf(
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true
    )
}

@Composable
fun MySimpleOutlineTf(
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        label = { Text(text = "Name") }
    )
}

@Composable
fun MyOutlineTfWithIcons(
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        label = { Text(text = "Name") },
        placeholder = { Text(text = "example@gamil.com") },
        leadingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = null) },
        trailingIcon = { Icon(imageVector = Icons.Filled.Info, contentDescription = null) }
    )
}

@Composable
fun MyOutlineTfWithPreSuffix(
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        label = { Text(text = "PreSuffix") },
        prefix = { Text(text = "www.") },
        suffix = { Text(text = ".com") },
        placeholder = { Text(text = "google") },
//        leadingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = null) },
//        trailingIcon = { Icon(imageVector = Icons.Filled.Info, contentDescription = null) }
    )
}


@Composable
fun MyOutlineTfWithSupportingText(
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Title") },
        supportingText = {
            Text("Supporting text that is long and perhaps goes onto another line.")
        },
    )
}

@Composable
fun MyOutlineTfWithKeyboardOption(
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("PredefinedValue") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        label = { Text(text = "KeyOption") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Ascii,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )
    )
}

@Composable
fun MyOutlineTfWithPasswordInput(
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        label = { Text(text = "Password") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )
    )
}

@Composable
fun MyOutlineTfWithErrorSuccessState(
    modifier: Modifier = Modifier
) {
    val errorMessage = "Text input too long"
    var text by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    val charLimit = 10

    fun validate(text: String) {
        isError = text.length > charLimit
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            validate(text)
        },
        singleLine = true,
        label = { Text(if (isError) "Username*" else "Username") },
        supportingText = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Limit: ${text.length}/$charLimit",
                textAlign = TextAlign.End,
            )
        },
        isError = isError,
        keyboardActions = KeyboardActions { validate(text) },
        modifier = Modifier.semantics {
            // Provide localized description of the error
            if (isError) error(errorMessage)
        }
    )
}

@Composable
fun MyOutlineTfWithTextArea(
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable {
        mutableStateOf(
            "This is a very long input that extends beyond " +
                    "the height of the text area." +
                    "This is a very long input that extends beyond " +
                    "the height of the text area."
        )
    }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .height(100.dp)
            .width(TextFieldDefaults.MinWidth),
        label = { Text("Title") }
    )
}
