package com.example.packagem3.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.packagem3.core.MyModalNavigationDrawer

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    MyModalNavigationDrawer(
//        scaffoldContent = MyHomeScreenContents(),
        modifier = modifier
    )
}

@Composable
fun MyHomeScreenContents(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "This is Artist Screen.")
    }
}