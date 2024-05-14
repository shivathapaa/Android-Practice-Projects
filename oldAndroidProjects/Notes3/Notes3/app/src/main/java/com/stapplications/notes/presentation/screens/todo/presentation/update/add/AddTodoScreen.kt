package com.stapplications.notes.presentation.screens.todo.presentation.update.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.AssistChip
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stapplications.notes.NoteViewModelProvider
import com.stapplications.notes.R
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AddTodoScreen(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    viewModel: AddTodoViewModel = viewModel(factory = NoteViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    val todoUiState = viewModel.todoUiState

    var showTimePicker by remember { mutableStateOf(false) }
    val state = rememberTimePickerState()
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    val snackState = remember { SnackbarHostState() }
    val showingPicker = remember { mutableStateOf(true) }
    val snackScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current

    var showDatePicker by remember { mutableStateOf(false) }

    val textFieldCustomColors: TextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent, // Hide focused state indicator
        unfocusedIndicatorColor = Color.Transparent, // Hide unfocused state indicator
        disabledIndicatorColor = Color.Transparent, // Hide disabled state indicator...
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent
    )

    val focusRequester = remember { FocusRequester() }
    val isChecked by remember { mutableStateOf(false) }

    var showCheckListTitle by remember { mutableStateOf(false) }
    val listContainsMoreThanOne by remember {
        derivedStateOf { todoUiState.todoDetails.body.size > 1 }
    }

    var saveEnabled by remember { mutableStateOf(false) }

    var combinedMillis by rememberSaveable {
        mutableStateOf("")
    }

    BasicAlertDialog(onDismissRequest = {
        coroutineScope.launch {
            viewModel.updateUiState(todoUiState.todoDetails.copy(reminder = combinedMillis))
            viewModel.saveTodo()
            combinedMillis = ""
            onDismiss()
        }
        onDismiss()
    }) {
        Surface(
            modifier = modifier
                .wrapContentSize()
                .animateContentSize(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.small_padding),
                        bottom = dimensionResource(id = R.dimen.large_padding)
                    )
                    .verticalScroll(rememberScrollState())
                    .animateContentSize(),
//                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding))
            ) {
                AnimatedVisibility(visible = showCheckListTitle) {
                    OutlinedTextField(
                        modifier = Modifier.animateEnterExit(),
                        value = todoUiState.todoDetails.title,
                        onValueChange = {
                            viewModel.updateUiState(todoUiState.todoDetails.copy(title = it))
//                            textFields = textFields.toMutableList().apply { set(0, it) }
                        },
                        colors = textFieldCustomColors,
                        textStyle = MaterialTheme.typography.titleMedium,
                        placeholder = {
                            Text(
                                text = "Checklist of subtasks",
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                todoUiState.todoDetails.body.forEachIndexed { index, text ->
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimensionResource(id = R.dimen.small_padding))
                            .focusRequester(focusRequester)
                            .onKeyEvent { // For hardware keyboard
                                if (it.key == Key.Enter) {
                                    if (text.first.isNotBlank()) {
                                        showCheckListTitle = true
                                        viewModel.addListItem(index + 1, "")
                                    }
                                }

                                if (it.key == Key.Backspace) {
                                    if (text.first.isBlank() && listContainsMoreThanOne) {
                                        viewModel.removeListItem(index)
                                    }
                                    if (listContainsMoreThanOne && todoUiState.todoDetails.title.isBlank()) {
                                        showCheckListTitle = false
                                    }
                                    true
                                } else {
                                    false
                                }
                            }
                            .animateContentSize(),
                        value = text.first,
                        onValueChange = {
                            viewModel.updateListItem(index, it)
                        },
                        leadingIcon = {
                            val icon =
                                if (isChecked) R.drawable.checked_box
                                else R.drawable.unchecked_box

                            Icon(
                                modifier = Modifier.alpha(0.38f),
                                painter = painterResource(id = icon),
                                contentDescription = null
                            )
                        },
                        colors = textFieldCustomColors,
                        placeholder = {
                            if (todoUiState.todoDetails.body.size == 1) {
                                Text(text = stringResource(R.string.tap_enter_to_create_subtask))
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            if (text.first.isNotBlank()) {
                                showCheckListTitle = true
                                viewModel.addListItem(index + 1, "")
                            }
                        })
                    )
                    coroutineScope.launch {
                        saveEnabled = text.first.isNotBlank() || todoUiState.todoDetails.title.isNotBlank()
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
                DialogActions(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(id = R.dimen.large_padding)),
                    onDismiss = onDismiss,
                    onSave = {
                        coroutineScope.launch {
                            viewModel.updateUiState(todoUiState.todoDetails.copy(reminder = combinedMillis))
                            viewModel.saveTodo()
                            combinedMillis = ""
                            onDismiss()
                        }
                    },
                    onShowDateTimePicker = { showDatePicker = true },
                    saveEnabled = saveEnabled
                )
            }
        }
    }

    var pickedDateInMillis by remember { mutableLongStateOf(631152000000) }

    // TODO demo how to read the selected date from the state.
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showTimePicker = true
                        pickedDateInMillis = datePickerState.selectedDateMillis ?: 631152000000
                    },
                    enabled = confirmEnabled.value
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.watch_time),
                            contentDescription = "Time Picker"
                        )
                        Text(stringResource(R.string.pick_time))
                    }
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }


    if (showTimePicker) {
        TimePickerDialog(
            title = if (showingPicker.value) {
                stringResource(R.string.select_time)
            } else {
                stringResource(R.string.enter_time)
            },
            onCancel = { showTimePicker = false },
            onConfirm = {
                val pickedTimeCal = Calendar.getInstance()
                coroutineScope.launch {
                    // a calendar instance with the selected date
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.timeInMillis = pickedDateInMillis
                    // set the time part from the current time
                    selectedCalendar.set(Calendar.HOUR_OF_DAY, pickedTimeCal.get(Calendar.HOUR_OF_DAY))
                    selectedCalendar.set(Calendar.MINUTE, pickedTimeCal.get(Calendar.MINUTE))
                    selectedCalendar.set(Calendar.SECOND, pickedTimeCal.get(Calendar.SECOND))

                    combinedMillis = LocalDateTime.ofInstant(Instant.ofEpochMilli(selectedCalendar.timeInMillis),  ZoneId.systemDefault()).toString()
                }
                showTimePicker = false
                showDatePicker = false
            },
            toggle = {
                if (configuration.screenHeightDp > 400) {
                    IconButton(onClick = { showingPicker.value = !showingPicker.value }) {
                        val icon = if (showingPicker.value) {
                            R.drawable.keyboard
                        } else {
                            R.drawable.watch_time
                        }
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = if (showingPicker.value) {
                                "Switch to Text Input"
                            } else {
                                "Switch to Touch Input"
                            }
                        )
                    }
                }
            }
        ) {
            if (showingPicker.value && configuration.screenHeightDp > 400) {
                TimePicker(state = state)
            } else {
                TimeInput(state = state)
            }
        }
    }
}

@Composable
fun DialogActions(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    onShowDateTimePicker: () -> Unit,
    saveEnabled: Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AssistChip(
            onClick = onShowDateTimePicker,
            label = {
                Text(
                    text = stringResource(R.string.set_reminder)
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.add_alarm),
                    contentDescription = null
                )
            }
        )
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.cancel))
            }
            TextButton(
                onClick = onSave,
                enabled = saveEnabled
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}

@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onCancel) {
                        Text("Cancel")
                    }
                    TextButton(onClick = onConfirm) {
                        Text("OK")
                    }
                }
            }
        }
    }
}
