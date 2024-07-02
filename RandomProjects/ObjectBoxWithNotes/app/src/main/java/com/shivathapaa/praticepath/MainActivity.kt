package com.shivathapaa.praticepath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.shivathapaa.praticepath.presentation.home.HomeScreen
import com.shivathapaa.praticepath.presentation.home.NotesListScreen
import com.shivathapaa.praticepath.ui.theme.PraticePathTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        PraticePathTheme {
                HomeScreen(
                    navigateToAddNote = { /*TODO*/ },
                    navigateToEdit = {}
                )
            }
        }
    }
}
