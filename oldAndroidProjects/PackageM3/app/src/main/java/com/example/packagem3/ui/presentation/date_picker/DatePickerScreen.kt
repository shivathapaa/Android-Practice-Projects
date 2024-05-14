package com.example.packagem3.ui.presentation.date_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        var showTextFieldForDateInput by remember { mutableStateOf(false) }
        var showModalDateInput by remember { mutableStateOf(false) }
        var showModalDatePicker by remember { mutableStateOf(false) }
//        var showDockedDatePicker by remember { mutableStateOf(false) }


        val handelDismissEventOfSiSimpleDateInputWithTextField: (Boolean) -> Unit =
            { showTextFieldForDateInput = false }
        val handelDismissEventOfShowModalDateInput: (Boolean) -> Unit =
            { showModalDateInput = false }
        val handelDismissEventOfShowModalDatePicker: (Boolean) -> Unit =
            { showModalDatePicker = false }
//        val handelDismissEventOfShowDockedDatePicker: (Boolean) -> Unit =
//            { showDockedDatePicker = false }

        if (!showTextFieldForDateInput) {
            Button(onClick = { showTextFieldForDateInput = true }) {
                Icon(imageVector = Icons.Rounded.CalendarMonth, contentDescription = null)
                Text(text = "Text Field Date Input")
            }
        }

        if (showTextFieldForDateInput) {
            MySimpleDateInputWithTextField(openTextFieldForDateInput = handelDismissEventOfSiSimpleDateInputWithTextField)
        }

        Button(onClick = { showModalDateInput = true }) {
            Icon(imageVector = Icons.Rounded.CalendarMonth, contentDescription = null)
            Text(text = "Modal Date Input")
        }

        if (showModalDateInput) {
            MyModalDateInput(showModalDateInput = handelDismissEventOfShowModalDateInput)
        }

        Button(onClick = { showModalDatePicker = true }
        ) {
            Icon(imageVector = Icons.Rounded.CalendarMonth, contentDescription = null)
            Text(text = "Modal Date Picker")
        }

        if (showModalDatePicker) {
            MyModalDatePicker(showModalDatePicker = handelDismissEventOfShowModalDatePicker)
        }

        FilledTonalButton(
            onClick = {
                showTextFieldForDateInput = false
                showModalDateInput = false
                showModalDatePicker = false
            }
        ) {
            Text(text = "Close if opened.")
            Icon(imageVector = Icons.Rounded.Close, contentDescription = null)
        }

//        Button(onClick = { showDockedDatePicker = true }) {
//            Icon(imageVector = Icons.Rounded.CalendarMonth, contentDescription = null)
//            Text(text = "Docked Date Picker")
//        }
//
//        if (showDockedDatePicker) {
//            MyDockedDatePicker(showDockedDatePicker = handelDismissEventOfShowDockedDatePicker)
//        }
    }
}

@Composable
fun MySimpleDateInputWithTextField(
    openTextFieldForDateInput: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var showSimpleDateInputWithoutDialog by remember { mutableStateOf(false) }

    val handelDismissEventOfSimpleDateInputWithTextField: (Boolean) -> Unit =
        { showSimpleDateInputWithoutDialog = false }

    var selectedDate by remember { mutableStateOf("") }
//    var selectedDate by remember { mutableStateOf(LocalDate.now().toString()) }

    val getDateTimeStamp: (Long?) -> Unit = { receivedLongDateTimeMilli ->
        // Convert timestamp to LocalDateTime
        val dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(receivedLongDateTimeMilli ?: 0),
            ZoneId.systemDefault()
        )
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
        selectedDate = formatter.format(dateTime)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { selectedDate = it },
            placeholder = { Text(text = "MM/DD/YYYY") },
            enabled = !showSimpleDateInputWithoutDialog,
            trailingIcon = {
                IconButton(onClick = { showSimpleDateInputWithoutDialog = true }) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        if (showSimpleDateInputWithoutDialog) {
            MyModalDateInput(
                selectedDate = getDateTimeStamp,
                showModalDateInput = handelDismissEventOfSimpleDateInputWithTextField
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyModalDateInput(
    showModalDateInput: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    selectedDate: (Long?) -> Unit = {},
) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }

    DatePickerDialog(
        onDismissRequest = { showModalDateInput(false) },
        confirmButton = {
            TextButton(
                onClick = {
                    selectedDate(datePickerState.selectedDateMillis)
                    showModalDateInput(false)
                },
                enabled = confirmEnabled.value
            ) {
                Text(text = "Done")
            }
        },
        dismissButton = {
            TextButton(onClick = { showModalDateInput(false) }) {
                Text(text = "Cancel")
            }
        },
        modifier = modifier
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyModalDatePicker(
    showModalDatePicker: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val datePickerState = rememberDatePickerState()
    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }

    DatePickerDialog(
        onDismissRequest = { showModalDatePicker(false) },
        confirmButton = {
            TextButton(
                onClick = {
                    showModalDatePicker(false)
                },
                enabled = confirmEnabled.value
            ) {
                Text(text = "Done")
            }
        },
        dismissButton = {
            TextButton(onClick = { showModalDatePicker(false) }) {
                Text(text = "Cancel")
            }
        },
        modifier = modifier
    ) {
        DatePicker(state = datePickerState)
    }
}

// DockedDataPicker will be available in future

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyDockedDatePicker(
//    showDockedDatePicker: (Boolean) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val datePickerState = rememberDatePickerState()
//    val confirmEnabled = remember {
//        derivedStateOf { datePickerState.selectedDateMillis != null }
//    }
//
//    DatePickerDialog(
//        onDismissRequest = { showDockedDatePicker(false) },
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    showDockedDatePicker(false)
//                },
//                enabled = confirmEnabled.value
//            ) {
//                Text(text = "Done")
//            }
//        },
//        dismissButton = {
//            TextButton(onClick = { showDockedDatePicker(false) }) {
//                Text(text = "Cancel")
//            }
//        },
//        modifier = modifier
//    ) {
//        DatePicker(state = datePickerState)
//    }
//}
