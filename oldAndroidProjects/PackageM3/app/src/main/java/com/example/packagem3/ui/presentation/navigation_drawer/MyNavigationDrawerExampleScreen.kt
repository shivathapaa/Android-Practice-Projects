package com.example.packagem3.ui.presentation.navigation_drawer

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
import androidx.compose.material3.ListItem
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.packagem3.R
import com.example.packagem3.ui.presentation.search.SearchBarScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MyNavigationDrawerExampleScreen(
    modifier: Modifier = Modifier
) {
    MyModalNavigationDrawer(modifier = modifier)
}

@Composable
fun MyModalNavigationDrawer(
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val selectedItem = remember { mutableStateOf(drawerItemList[0]) }

    ModalNavigationDrawer(
        drawerContent = {
            MyDrawerContent(
                scope = scope,
                drawerState = drawerState,
                selectedItem = selectedItem,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.navigation_drawer_width))
//                modifier = Modifier.Modifier.fillMaxWidth(0.7f)  // Not good pratice
            )
        },
        drawerState = drawerState,
        modifier = modifier
    ) {
        SearchBarScreen(
            onMenuClick = { scope.launch { drawerState.open() } }
        )
    }
}

@Composable
fun MyDrawerContent(
    scope: CoroutineScope,
    drawerState: DrawerState,
    selectedItem: MutableState<Pair<String, ImageVector>>,
    modifier: Modifier = Modifier
) {
    val verticalScrollState = rememberScrollState()

    ModalDrawerSheet(
        drawerShape = RectangleShape,
//        drawerShape = RoundedCornerShape(bottomEnd = dimensionResource(id = R.dimen.medium_padding)),
        // removes top padding of the status bar for drawerSheet
        windowInsets = WindowInsets(top = 0.dp),
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(verticalScrollState)
    ) {
        SupportingInfoBoxWithProfile(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))

        drawerItemList.forEach { item ->
            NavigationDrawerItem(
                label = { Text(text = item.first) },
                icon = { Icon(imageVector = item.second, contentDescription = null) },
                selected = item == selectedItem.value,
                onClick = {
                    scope.launch { drawerState.close() }
                    selectedItem.value = item
                },
                shape = RoundedCornerShape(
                    topEndPercent = 100,
                    bottomEndPercent = 100
                ),
                modifier = Modifier
//                    .padding(end = dimensionResource(id = R.dimen.small_padding))
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
fun SupportingInfoBoxWithProfile(
    modifier: Modifier = Modifier,
    onArtistClick: () -> Unit = {},
) {
    val drawerContainerColor = DrawerDefaults.containerColor
    val myBrush = Brush.verticalGradient(
        listOf(
            Color.Transparent,
            drawerContainerColor.copy(alpha = 0.7f),
            drawerContainerColor.copy(alpha = 1.0f)
        )
    )


//    val myColorStops = arrayOf(
//        0.0f to Color.Transparent,
//        1f to drawerContainerColor
//    )

//    val myBrush = Brush.verticalGradient(colorStops = myColorStops)

    Box(
        modifier = modifier
            .clickable(onClick = onArtistClick)
    ) {
        Row(
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
        MyCustomListItem(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        )
    }
}

@Composable
fun MyCustomListItem(
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
                .size(56.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
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


//In Compose, elements within a Box layout are stacked on top of each other by default,
// but the ListItem component has its own built-in layout logic that prevents it from overlapping other elements.
@Composable
fun CannotUseInBoxListItem(
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier,
        headlineContent = { Text(text = "K chha ho babu?") },
        supportingContent = { Text(text = "Ma, Ma ra Ma Kaji") },
        leadingContent = {
            Image(
                painter = painterResource(id = R.drawable.cow_farm),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
        }
    )
}


val drawerItemList: List<Pair<String, ImageVector>> = listOf(
    Pair("Library", Icons.Outlined.LibraryMusic),
    Pair("Playing Queue", Icons.AutoMirrored.Filled.PlaylistPlay),
    Pair("Folders", Icons.Outlined.Folder),
    Pair("Equalizer", Icons.Outlined.Equalizer),
    Pair("Scan Media", Icons.Outlined.Scanner),
    Pair("Setting", Icons.Outlined.Settings),
    Pair("About", Icons.Outlined.Info),
)
