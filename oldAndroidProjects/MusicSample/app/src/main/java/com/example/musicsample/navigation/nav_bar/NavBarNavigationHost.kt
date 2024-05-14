package com.example.musicsample.navigation.nav_bar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicsample.feature.nav_bar_feature.album.MusicAlbumScreen
import com.example.musicsample.feature.nav_bar_feature.artist.MusicArtistScreen
import com.example.musicsample.feature.nav_bar_feature.genre.MusicGenreScreen
import com.example.musicsample.feature.nav_bar_feature.home.MusicHomeScreen
import com.example.musicsample.feature.nav_bar_feature.song.MusicSongScreen

@Composable
fun NavBarNavigationHost(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestinationNavBar.route,
        modifier = modifier
    ) {
        composable(route = HomeDestinationNavBar.route) {
            MusicHomeScreen(snackbarHostState = snackbarHostState)
        }
        composable(route = SongDestinationNavBar.route) {
            MusicSongScreen()
        }
        composable(route = ArtistDestinationNavBar.route) {
            MusicArtistScreen()
        }
        composable(route = AlbumDestinationNavBar.route) {
            MusicAlbumScreen()
        }
        composable(route = GenreDestinationNavBar.route) {
            MusicGenreScreen()
        }
    }
}

//fun NavGraphBuilder.addNavigationBarNestedGraph(
//    navController: NavHostController,
//    modifier: Modifier = Modifier
//) {
//    navigation(
//        startDestination = HomeDestinationNavBar.route,
//        route = "bottom_bar"
//    ) {
//        composable(route = HomeDestinationNavBar.route) {
//            MusicHomeScreen(modifier = modifier)
//        }
//        composable(route = SongDestinationNavBar.route) {
//            MusicSongScreen(modifier = modifier)
//        }
//        composable(route = ArtistDestinationNavBar.route) {
//            MusicArtistScreen(modifier = modifier)
//        }
//        composable(route = AlbumDestinationNavBar.route) {
//            MusicAlbumScreen(modifier = modifier)
//        }
//        composable(route = GenreDestinationNavBar.route) {
//            MusicGenreScreen(modifier = modifier)
//        }
//    }
//}
