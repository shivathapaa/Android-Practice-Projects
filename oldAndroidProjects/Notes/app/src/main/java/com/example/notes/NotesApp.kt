package com.example.notes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notes.navigation.NotesNavigationHost

@Composable
fun NotesApp(navController: NavHostController = rememberNavController()) {
    NotesNavigationHost(navController = navController)
}