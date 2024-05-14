package com.example.packagem3.ui.presentation.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.packagem3.R

@Composable
fun PracticeDialogScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val showBasicDialog = remember { mutableStateOf(false) }

        FilledTonalButton(onClick = { showBasicDialog.value = true }) {
            Text(text = "Basic Dialog Of Alert")
        }

        if (showBasicDialog.value) {
            BasicDialogOfAlert()
        }

        val showDialogOfAlert = remember { mutableStateOf(false) }

        FilledTonalButton(onClick = { showDialogOfAlert.value = true }) {
            Text(text = "Dialog Of Alert")
        }

        if (showDialogOfAlert.value) {
            DialogOfAlert()
        }
        val showDialogOfAlertWithIcon = remember { mutableStateOf(false) }

        FilledTonalButton(onClick = { showDialogOfAlertWithIcon.value = true }) {
            Text(text = "Dialog Of Alert With Icon")
        }

        if (showDialogOfAlertWithIcon.value) {
            DialogOfAlertWithIcon()
        }

        val showFullScreenDialogOfAlert = remember { mutableStateOf(false) }

        FilledTonalButton(onClick = { showFullScreenDialogOfAlert.value = true }) {
            Text(text = "Full Screen Dialog Of Alert")
        }

        if (showFullScreenDialogOfAlert.value) {
            FullScreenDialogOfAlert()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicDialogOfAlert(
    modifier: Modifier = Modifier
) {
    val showDialog = remember { mutableStateOf(true) }

    if (showDialog.value) {
        BasicAlertDialog(
            onDismissRequest = { showDialog.value = false },
            modifier = modifier
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding))
                ) {
                    Text(text = "This is just an example of the alert dialog. This is the text message that you would want to show to the user.")
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
                    TextButton(
                        onClick = {
                            showDialog.value = false
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}


@Composable
fun DialogOfAlert(
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "Dismiss")
                }
            },
            title = { Text(text = "Delete") },
            text = {
                Text(text = "This is just an example of the alert dialog. This is the text message that you would want to show to the user.")
            },
//            shape = ,
//            tonalElevation = ,
            modifier = modifier
        )
    }

}


@Composable
fun DialogOfAlertWithIcon(
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "Dismiss")
                }
            },
            title = { Text(text = "Delete") },
            text = {
                Text(text = "This is just an example of the alert dialog. This is the text message that you would want to show to the user.")
            },
            icon = { Icon(imageVector = Icons.Rounded.Delete, contentDescription = null) },
//            shape = ,
//            tonalElevation = ,
            modifier = modifier
        )
    }
}

@Composable
fun FullScreenDialogOfAlert(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) { Text(text = "Will implement when usage will be found.") }
}