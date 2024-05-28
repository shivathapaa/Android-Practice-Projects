package com.example.kathmandu.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kathmandu.R
import com.example.kathmandu.data.local.Categories.destinationCategories


enum class Screen(@StringRes val title: Int) {
    Start(title = R.string.kathmandu_city),
    Categories(title = R.string.categories),
    Temples(title = R.string.temples),
    Resorts(title = R.string.resorts),
    ShoppingMalls(title = R.string.shopping_malls),
    Casinos(title = R.string.casinos),
    ArtGallery(title = R.string.art_gallery),
    MovieTheatre(title = R.string.movie_threatre)
}


@Composable
fun KathmanduHomeScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Start.name
    )

    Scaffold(
        topBar = {
            AppTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Start.name,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(route = Screen.Start.name) {
                KathmanduDescription(
                    imageHighlight = R.drawable.manakamana_temple,
                    onVisitClicked = { navController.navigate(Screen.Categories.name) }
                )
            }
            composable(route = Screen.Categories.name) {
                DestinationCategories(
                    categoryList = destinationCategories,
                    onDestinationSelected = { navController.navigate(Screen.Temples.name) }
                )
            }
            composable(route = Screen.Temples.name) {
                DestinationDisplayCard(
                    imageRes = R.drawable.talbarahi_temple,
                    name = R.string.talbarahi_temple,
                    description = R.string.talbarahi_temple_description,
                    rating = R.string.gokarna_forest_resort_rating,
                    location = R.string.ballyS_casino_location,
                    contact = R.string.ballyS_casino_contact,
                )
            }
        }
    }
}

@Composable
fun KathmanduDescription(
    @DrawableRes imageHighlight: Int,
    onVisitClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.large_padding))
            .verticalScroll(enabled = true, state = rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = imageHighlight),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.introduction_image_height))
                .padding(vertical = dimensionResource(id = R.dimen.medium_padding))
                .clip(MaterialTheme.shapes.small)
        )
        KathmanduWithVisitButton(
            onVisitClicked = onVisitClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.medium_padding))
        )
        Text(
            text = stringResource(id = R.string.introduction_of_kathmandu),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.medium_padding))
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentScreen: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title))},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = navigateUp
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun KathmanduWithVisitButton(
    onVisitClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        KathmanduCityName(Modifier.align(Alignment.Top))
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onVisitClicked
        ) {
            Text(text = stringResource(id = R.string.visit_city))
        }
    }
}

@Composable
fun KathmanduCityName(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ){
        Text(
            text = stringResource(id = R.string.kathmandu_city),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(id = R.string.capital_of_nepal),
            style = MaterialTheme.typography.bodySmall
        )
    }
}



@Preview
@Composable
fun KathmanduHomeScreenPreview(){
    KathmanduHomeScreen()
}