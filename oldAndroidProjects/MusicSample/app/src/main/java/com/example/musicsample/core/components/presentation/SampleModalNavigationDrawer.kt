package com.example.musicsample.core.components.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicsample.R
import com.example.musicsample.navigation.nav_drawer.listOfNavDrawerDestination
import kotlinx.coroutines.launch

@Composable
fun SampleModalNavigationDrawer(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val verticalScrollState = rememberScrollState()

    val navigationDrawerScreenDestinations = listOfNavDrawerDestination

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    ModalNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet(
                modifier = modifier
                    .width(dimensionResource(id = R.dimen.navigation_drawer_width))
                    .fillMaxHeight()
                    .verticalScroll(verticalScrollState),
                drawerShape = RoundedCornerShape(bottomEnd = dimensionResource(id = R.dimen.medium_padding)),
                // removes top padding of the status bar for drawerSheet since it applies vertical and start insets to content.
                windowInsets = WindowInsets(top = 0.dp),
            ) {
                BoxWithProfileForNavDrawer(
                    onArtistClick = { /*TODO*/ },
                    onAlbumClick = { /*TODO*/ })

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))

                navigationDrawerScreenDestinations.forEach { screen ->
                    NavigationDrawerItem(
                        label = { Text(text = stringResource(id = screen.titleRes)) },
                        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            coroutineScope.launch { drawerState.close() }
                        },
                        shape = RoundedCornerShape(
                            topEndPercent = 100,
                            bottomEndPercent = 100
                        ),
                        modifier = Modifier
                            .padding(
                                end = NavigationDrawerItemDefaults.ItemPadding.calculateEndPadding(
                                    LayoutDirection.Ltr
                                )
                            )
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        ScaffoldWithNavBarContents(
            drawerState = drawerState,
            navController = rememberNavController()
        )
    }
}


@Composable
fun BoxWithProfileForNavDrawer(
    onArtistClick: () -> Unit,
    onAlbumClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val drawerContainerColor = DrawerDefaults.containerColor

    val myBrush = Brush.verticalGradient(
        listOf(
            Color.Transparent,
            drawerContainerColor.copy(alpha = 0.7f),
            drawerContainerColor.copy(alpha = 1.0f)
        )
    )

    Box(
        modifier = modifier
            .clickable(onClick = onAlbumClick)
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer { alpha = 0.99f }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = myBrush,
                        blendMode = BlendMode.DstOut
                    )
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.album_cover),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Crop because the width of Drawer, and height of the cover art is defined (otherwise use fillWidth)
//                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.navigation_drawer_image_cover_size))
            )
        }
        FakeListItemForNavDrawer(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            onArtistClick = onArtistClick
        )
    }
}

@Composable
fun FakeListItemForNavDrawer(
    onArtistClick: () -> Unit,
    modifier: Modifier = Modifier,
    showTail: Boolean = false,
    color: Color = Color.Transparent
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(color)
            .padding(
                start = dimensionResource(id = R.dimen.medium_padding),
                end = dimensionResource(id = R.dimen.extra_small_padding),
                top = dimensionResource(id = R.dimen.extra_small_padding),
                bottom = dimensionResource(id = R.dimen.extra_small_padding),
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagine_dragons_artist),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.nav_drawer_image_size))
                .clip(CircleShape)
                .clickable(onClick = onArtistClick)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(dimensionResource(id = R.dimen.medium_padding))
        ) {
            Text(
                text = "Bad Liar",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Imagine Dragons",
                style = MaterialTheme.typography.bodySmall
            )
        }
        if (showTail) {
            IconButton(
                onClick = { /*TODO*/ },
//                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.medium_padding))
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        }
    }
}