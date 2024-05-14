package com.example.packagem3.core

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.PlaylistPlay
import androidx.compose.material.icons.outlined.Equalizer
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Scanner
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.packagem3.R
import com.example.packagem3.core.components.MyScaffoldWithContents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MyModalNavigationDrawer(
//    scaffoldContent: @Composable (Modifier) -> Unit,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val selectedCategory = remember { mutableStateOf(navigationDrawerCategoryList[0]) }

    ModalNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            MyNavigationDrawerContent(
                scope = coroutineScope,
                drawerState = drawerState,
                selectedCategory = selectedCategory,
                onArtistClick = { coroutineScope.launch { drawerState.close() /* TODO: passClick */ } },
                onAlbumClick = { coroutineScope.launch { drawerState.close() /* TODO: passClick */ } },
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.navigation_drawer_width))
//                modifier = Modifier.Modifier.fillMaxWidth(0.7f)  // Not good practice
            )
        },
        drawerState = drawerState
    ) {
        MyScaffoldWithContents(
//            scaffoldContent = scaffoldContent,
            drawerState = drawerState
        )
    }
}

@Composable
fun MyNavigationDrawerContent(
    scope: CoroutineScope,
    drawerState: DrawerState,
    selectedCategory: MutableState<Pair<String, ImageVector>>,
    onArtistClick: () -> Unit,
    onAlbumClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val verticalScrollState = rememberScrollState()

    ModalDrawerSheet(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(verticalScrollState),
        drawerShape = RoundedCornerShape(bottomEnd = dimensionResource(id = R.dimen.medium_padding)),
        // removes top padding of the status bar for drawerSheet
        windowInsets = WindowInsets(top = 0.dp),
    ) {
        BoxWithProfileForMyNavDrawer(onArtistClick = onArtistClick, onAlbumClick = onAlbumClick)

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))

        navigationDrawerCategoryList.forEach { category ->
            NavigationDrawerItem(
                label = { Text(text = category.first) },
                icon = { Icon(imageVector = category.second, contentDescription = null) },
                selected = category == selectedCategory.value,
                onClick = {
                    scope.launch { drawerState.close() }
                    selectedCategory.value = category
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

}

@Composable
fun BoxWithProfileForMyNavDrawer(
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
        MyCustomListItemForNavDrawer(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            onArtistClick = onArtistClick
        )
    }
}

@Composable
fun MyCustomListItemForNavDrawer(
    onArtistClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.medium_padding),
                vertical = dimensionResource(id = R.dimen.extra_small_padding)
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
    }
}

val navigationDrawerCategoryList: List<Pair<String, ImageVector>> = listOf(
    Pair("Library", Icons.Outlined.LibraryMusic),
    Pair("Playing Queue", Icons.AutoMirrored.Filled.PlaylistPlay),
    Pair("Folders", Icons.Outlined.Folder),
    Pair("Equalizer", Icons.Outlined.Equalizer),
    Pair("Scan Media", Icons.Outlined.Scanner),
    Pair("Setting", Icons.Outlined.Settings),
    Pair("About", Icons.Outlined.Info),
)
