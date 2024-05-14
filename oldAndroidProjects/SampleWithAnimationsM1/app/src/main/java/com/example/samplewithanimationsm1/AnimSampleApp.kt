package com.example.samplewithanimationsm1

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.samplewithanimationsm1.core.presentation.components.AnimNavDrawer

@Composable
fun AnimSampleApp(
    modifier: Modifier = Modifier
) {
    AnimNavDrawer(modifier = modifier)
}