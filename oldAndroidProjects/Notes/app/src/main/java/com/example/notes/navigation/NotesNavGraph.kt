package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notes.ui.screen.details.AddNoteDestination
import com.example.notes.ui.screen.details.AddNoteScreen
import com.example.notes.ui.screen.edit.EditNoteDestination
import com.example.notes.ui.screen.edit.EditNoteScreen
import com.example.notes.ui.screen.home.HomeDestination
import com.example.notes.ui.screen.home.HomeScreen

@Composable
fun NotesNavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToAddNotes = { navController.navigate(AddNoteDestination.route) },
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateToNoteEdit = { navController.navigate("${EditNoteDestination.route}/${it}") }
            )
        }
        composable(route = AddNoteDestination.route) {
            AddNoteScreen(
                navigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() },
                canNavigateBack = navController.previousBackStackEntry != null
            )
        }
        composable(
            route = EditNoteDestination.routeWithArgs,
            arguments = listOf(navArgument(EditNoteDestination.noteIdArg) {
                type = NavType.IntType
            })
        ) {
            EditNoteScreen(
                onNavigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() },
                canNavigateBack = navController.previousBackStackEntry != null,
            )
        }
    }
}