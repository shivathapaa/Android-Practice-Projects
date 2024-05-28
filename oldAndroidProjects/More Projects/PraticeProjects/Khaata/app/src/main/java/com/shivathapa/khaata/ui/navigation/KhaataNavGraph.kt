package com.shivathapa.khaata.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shivathapa.khaata.ui.category.entry.CategoryEntryDestination
import com.shivathapa.khaata.ui.category.entry.CategoryEntryScreen
import com.shivathapa.khaata.ui.expenses.entry.ExpenseEntryDestination
import com.shivathapa.khaata.ui.expenses.entry.ExpenseEntryScreen
import com.shivathapa.khaata.ui.expenses.screen.ExpensesDestination
import com.shivathapa.khaata.ui.expenses.screen.ExpensesListScreen
import com.shivathapa.khaata.ui.home.HomeDestination
import com.shivathapa.khaata.ui.home.HomeScreen


@Composable
fun KhaataNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToCategoryEntry = { navController.navigate(CategoryEntryDestination.route) },
                navigateToExpensesScreen = { navController.navigate(ExpenseEntryDestination.route) }
            )
        }
        composable(route = CategoryEntryDestination.route) {
            CategoryEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
            )
        }
        composable(
            route = ExpensesDestination.routeWithArgs,
            arguments = listOf(navArgument(ExpensesDestination.categoryIdArg) {
                type = NavType.IntType
            })
        ) {
            ExpensesListScreen(
                onNavigateUp = { navController.navigateUp() },
                navigateToExpenseEdit = { navController.navigate(ExpenseEntryDestination.route) },
                navigateToExpenseEntry = { navController.navigate(ExpenseEntryDestination.route) }
            )
        }
        composable(route = ExpenseEntryDestination.route) {
            ExpenseEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}