package com.example.musicsample.feature.nav_bar_feature.artist

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.musicsample.R
import kotlinx.coroutines.launch

@Composable
fun MusicArtistScreen(
    modifier: Modifier = Modifier,
    artistViewModel: MusicArtistViewModel = viewModel()
) {
    val uiState by artistViewModel.uiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    val pickSingleImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            // use viewModel for actions
            artistViewModel.addUri(uri)
        }
    )

    val pickMultipleImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            // use viewModel for actions
            artistViewModel.addUri(uris)
        }
    )

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        FilledTonalButton(onClick = {
            coroutineScope.launch {
                pickSingleImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }) {
            Icon(imageVector = Icons.Default.Image, contentDescription = null)
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
            Text(text = "Pick a photo")
        }

        AsyncImage(
            model = uiState.selectedSingleImageUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(156.dp)
        )

        FilledTonalButton(onClick = {
            coroutineScope.launch {
                pickMultipleImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }) {
            Icon(imageVector = Icons.Default.Image, contentDescription = null)
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
            Text(text = "Pick multiple photos")
        }

        LazyVerticalStaggeredGrid(
            state = rememberLazyStaggeredGridState(),
            columns = StaggeredGridCells.Adaptive(minSize = 156.dp),
            contentPadding = PaddingValues(8.dp),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.selectedMultipleImageUri) { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}