package com.example.samplewithanimationsm1.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.samplewithanimationsm1.R

@Composable
fun DemoBg(
    modifier: Modifier = Modifier
) {
    Image(painter = painterResource(id = R.drawable.album_art), contentDescription = null)
}