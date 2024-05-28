package com.shivathapa.khaata.ui.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shivathapa.khaata.R
import com.shivathapa.khaata.ui.AppViewModelProvider
import com.shivathapa.khaata.ui.KhaataFloatingActionButton
import com.shivathapa.khaata.ui.KhaataTopAppBar
import com.shivathapa.khaata.ui.category.screen.CategoryList
import com.shivathapa.khaata.ui.common.fake.fakeListOfExpenseCategory
import com.shivathapa.khaata.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "Home"
    override val titleRes: Int = R.string.category
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToExpensesScreen: () -> Unit,
    navigateToCategoryEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            KhaataTopAppBar(
                title = stringResource(id = HomeDestination.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            KhaataFloatingActionButton(
                icon = Icons.Default.Add,
                text = stringResource(id = R.string.add),
                extended = lazyListState.isScrollingUp(),
                onClick = navigateToCategoryEntry
            )
        }
    ) { innerPadding ->
        CategoryList(
            navigateToExpensesScreen = navigateToExpensesScreen,
            lazyListState = lazyListState,
            expenseCategory = homeUiState.categoryList,
            contentPadding = innerPadding
        )
    }
}

/**
 * Returns whether the lazy list is currently scrolling up.
 */
@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableIntStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}