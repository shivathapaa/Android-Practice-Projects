package com.example.Amphibians.ui

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Amphibians.R
import com.example.Amphibians.ui.screens.HomeScreen
import com.example.Amphibians.ui.screens.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibianApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AmphibianTopAppBar(scrollBehavior = scrollBehavior)
        }
    ) { innerPadding ->
        val homeScreenViewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory)
        HomeScreen(
            retryAction = homeScreenViewModel::getAmphibiansDetail,
            amphibianUiState = homeScreenViewModel.amphibianUiState,
            contentPadding = innerPadding
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibianTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
   CenterAlignedTopAppBar(
       scrollBehavior = scrollBehavior,
       title = {
           Text(
               text = stringResource(id = R.string.amphibians),
               style = MaterialTheme.typography.headlineSmall
           )
       },
       modifier = modifier
   )
}