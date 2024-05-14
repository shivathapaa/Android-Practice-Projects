package com.stapplications.notes.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.stapplications.notes.presentation.home.HomeScreen
import com.stapplications.notes.presentation.screens.note.presentation.update.add.AddNoteScreen
import com.stapplications.notes.presentation.screens.note.presentation.update.edit.EditNoteScreen

@Composable
fun NotesNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val snackbarHostState = remember { SnackbarHostState() }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToAddNote = { navController.navigate(Screen.AddNote.route) },
                navigateToEdit = { navController.navigate(Screen.EditNote.passId(it)) },
                snackbarHostState = snackbarHostState
            )
        }
        composable(route = Screen.AddNote.route) {
            AddNoteScreen(
                navigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() },
                snackbarHostState = snackbarHostState
            )
        }
        composable(
            route = Screen.EditNote.route,
            arguments = listOf(
                navArgument(NOTE_ID_ARGUMENT_KEY) {
                    type = NavType.IntType
                }
            )
        ) {
            EditNoteScreen(
                navigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() },
                snackbarHostState = snackbarHostState
//                noteId = it.arguments!!.getInt(NOTE_ID_ARGUMENT_KEY)
            )
        }
    }
}
