package com.example.packagem3.ui.presentation.top_app_bar.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.packagem3.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExitUntilCollapsedLargeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
) {
    Box(
        modifier = modifier
    ) {
        LargeTopAppBar(
            title = {
                TopBarTitleWithImage()
            },
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.back_button
                            )
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = stringResource(
                            R.string.actions
                        )
                    )
                }
            },
            scrollBehavior = scrollBehavior,
            modifier = modifier
        )
    }
}

@Composable
fun TopBarTitleWithImage(
    modifier: Modifier = Modifier
) {
    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
//        ImageCover()
        Text(text = stringResource(R.string.large_top_app_bar))
    }
}

@Composable
fun ImageCover(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.cow_farm),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(dimensionResource(id = R.dimen.top_bar_image_size))
    )
}