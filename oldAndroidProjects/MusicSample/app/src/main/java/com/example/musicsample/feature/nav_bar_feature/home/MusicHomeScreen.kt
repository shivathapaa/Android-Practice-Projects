package com.example.musicsample.feature.nav_bar_feature.home

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.dimensionResource
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicsample.R
import kotlinx.coroutines.launch

@Composable
fun MusicHomeScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    viewModel: MusicHomeScreenViewModel = viewModel(factory = MusicHomeScreenViewModelFactory())
) {
    // region state initialization
    val state = viewModel.uiState
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val requestCameraPermission =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                viewModel.onPermissionChange(CAMERA, isGranted)
                // add photo logic
            } else {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Camera currently disabled due to denied permission.")
                }
            }
        }

    var showExplanationDialogForCameraPermission by remember { mutableStateOf(false) }
    if (showExplanationDialogForCameraPermission) {
        CameraExplanationDialog(
            onConfirm = {
                requestCameraPermission.launch(CAMERA)
                showExplanationDialogForCameraPermission = false
            },
            onDismiss = { showExplanationDialogForCameraPermission = false }
        )
    }

    val requestLocationPermission =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                viewModel.onPermissionChange(ACCESS_COARSE_LOCATION, isGranted)
                // fetch location
            } else {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Location currently disabled due to denied permission.")
                }
            }
        }

    var showExplanationDialogForLocationPermission by remember { mutableStateOf(false) }
    if (showExplanationDialogForLocationPermission) {
        LocationExplanationDialog(
            onConfirm = {
                requestLocationPermission.launch(ACCESS_COARSE_LOCATION)
                showExplanationDialogForLocationPermission = false
            },
            onDismiss = { showExplanationDialogForLocationPermission = false }
        )
    }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        FilledTonalButton(onClick = {
            when {
                state.hasCameraAccess -> coroutineScope.launch {
                    snackbarHostState.showSnackbar("Got Camera permission")
                }

                ActivityCompat.shouldShowRequestPermissionRationale(
                    context.getActivity(),
                    CAMERA
                ) ->
                    showExplanationDialogForCameraPermission = true

                else ->
                    requestCameraPermission.launch(CAMERA)

            }
        }) {
            Icon(imageVector = Icons.Default.Camera, contentDescription = null)
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
            Text(text = "Camera permission")
        }

        FilledTonalButton(onClick = {
            when {
                state.hasLocationAccess -> coroutineScope.launch { snackbarHostState.showSnackbar("Got location permission") }

                ActivityCompat.shouldShowRequestPermissionRationale(
                    context.getActivity(),
                    ACCESS_COARSE_LOCATION
                ) -> showExplanationDialogForLocationPermission = true

                else ->
                    requestLocationPermission.launch(ACCESS_COARSE_LOCATION)

            }
        }) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
            Text(text = "Location permission")
        }
    }
}

@Composable
fun CameraExplanationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Camera access") },
        text = { Text("MusicSample would like access to the camera to be able take picture for testing purpose.") },
        icon = {
            Icon(
                Icons.Filled.Camera,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.surfaceTint
            )
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Continue")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
fun LocationExplanationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Location access") },
        text = { Text("MusicSample would like access to your location for testing purpose.") },
        icon = {
            Icon(
                Icons.Filled.Explore,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.surfaceTint
            )
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Continue")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Dismiss")
            }
        }
    )
}

fun Context.getActivity(): Activity {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is Activity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}