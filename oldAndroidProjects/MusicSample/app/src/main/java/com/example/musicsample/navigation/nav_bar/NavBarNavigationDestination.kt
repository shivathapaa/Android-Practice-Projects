package com.example.musicsample.navigation.nav_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Album
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.musicsample.R

val listOfNavBarDestination: List<NavBarNavigationDestination> =
    listOf<NavBarNavigationDestination>(
        HomeDestinationNavBar,
        SongDestinationNavBar,
        ArtistDestinationNavBar,
        AlbumDestinationNavBar,
        GenreDestinationNavBar
    )

interface NavBarNavigationDestination {
    val route: String
    val titleRes: Int
    val outlinedIcon: ImageVector
    val filledIcon: ImageVector
}

object HomeDestinationNavBar : NavBarNavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.home
    override val filledIcon: ImageVector = Icons.Filled.Home
    override val outlinedIcon: ImageVector = Icons.Outlined.Home
}

object AlbumDestinationNavBar : NavBarNavigationDestination {
    override val route = "album"
    override val titleRes = R.string.albums
    override val filledIcon: ImageVector = Icons.Filled.Album
    override val outlinedIcon: ImageVector = Icons.Outlined.Album
}

object ArtistDestinationNavBar : NavBarNavigationDestination {
    override val route: String = "artist"
    override val titleRes: Int = R.string.artists
    override val filledIcon: ImageVector = Icons.Filled.Person
    override val outlinedIcon: ImageVector = Icons.Outlined.Person
}

object SongDestinationNavBar : NavBarNavigationDestination {
    override val route: String = "song"
    override val titleRes: Int = R.string.songs
    override val filledIcon: ImageVector = Icons.Filled.MusicNote
    override val outlinedIcon: ImageVector = Icons.Outlined.MusicNote
}

object GenreDestinationNavBar : NavBarNavigationDestination {
    override val route: String = "genre"
    override val titleRes: Int = R.string.genres
    override val filledIcon: ImageVector = Icons.Filled.MusicNote
    override val outlinedIcon: ImageVector = Icons.Outlined.MusicNote
}

//object AlbumArtistDestination : NavigationDestination {
//    override val route: String = "album_artist"
//    override val titleRes: Int = R.string.album_artists
//}
//
//object PlaylistDestination : NavigationDestination {
//    override val route: String = "playlist"
//    override val titleRes: Int = R.string.playlists
//}
//
//
//object FolderDestination : NavigationDestination {
//    override val route: String = "folder"
//    override val titleRes: Int = R.string.folders
//}
