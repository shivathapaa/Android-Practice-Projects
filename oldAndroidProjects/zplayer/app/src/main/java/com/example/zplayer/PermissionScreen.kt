package com.example.zplayer

import android.Manifest.permission.READ_MEDIA_AUDIO
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun MediaAudioPermissionScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: PermissionViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return PermissionViewModel(requireActivity()) as T
        }
    })
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val state = viewModel.uiState
    val context = LocalContext.current

    val requestMediaAudioPermission =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                if (isGranted) {
                    viewModel.onPermissionChange(
                        permission = READ_MEDIA_AUDIO,
                        isGranted = isGranted
                    )
                } else {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Media cannot be displayed due to denied permission.")
                    }
                }
            }
        )

    var showExplanationDialogForAudioPermission by remember { mutableStateOf(false) }
    if (showExplanationDialogForAudioPermission) {
        AudioExplanationDialog(
            onConfirm = {
                requestMediaAudioPermission.launch(READ_MEDIA_AUDIO)
                showExplanationDialogForAudioPermission = false
            },
            onDismiss = {
                showExplanationDialogForAudioPermission = false
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FilledTonalButton(
            onClick = {
                when {
                    state.hasAudioAccess -> coroutineScope.launch {
                        snackbarHostState.showSnackbar("Permission is already granted.")
                    }
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        context.getActivity(),
                        READ_MEDIA_AUDIO
                    ) -> showExplanationDialogForAudioPermission = true

                    else -> requestMediaAudioPermission.launch(READ_MEDIA_AUDIO)

                }
            },
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_audio_file_24),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
            Text(text = "Media storage")
        }
    }
}

private fun Context.getActivity(): Activity {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is Activity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}

@Composable
fun AudioExplanationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Audio media access") },
        text = { Text(text = "This app requires audio media access for reading all audio files.")},
        icon = {
               Icon(
                   painter = painterResource(id = R.drawable.baseline_audio_file_24),
                   contentDescription = null
               )
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = "Continue")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Dismiss")
            }
        }

    )
}
