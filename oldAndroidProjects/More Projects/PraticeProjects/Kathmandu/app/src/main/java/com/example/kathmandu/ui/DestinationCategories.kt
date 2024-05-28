package com.example.kathmandu.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kathmandu.R
import com.example.kathmandu.data.local.Categories.destinationCategories
import com.example.kathmandu.model.Category

@Composable
fun DestinationCategories(
    onDestinationSelected: () -> Unit = {},
    categoryList: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(categoryList) {
            CategoryCard(
                onDestinationSelected = onDestinationSelected,
                category = it.category,
                imageRes = it.imageRes
            )
        }
    }
}

@Composable
fun CategoryCard(
    onDestinationSelected: () -> Unit,
    @DrawableRes imageRes: Int,
    @StringRes category: Int,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.large_padding))
            .padding(top = dimensionResource(id = R.dimen.large_padding))
            .fillMaxWidth()
            .clickable(enabled = true, onClick = onDestinationSelected)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
//            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.3f) }),
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.image_height))
                .fillMaxWidth()
                .blur(
                    radiusX = 1.dp,
                    radiusY = 1.dp,
                    edgeTreatment = BlurredEdgeTreatment(MaterialTheme.shapes.medium)
                )
        )
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = category),
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.in_card_text_padding_large))
            )
        }

    }
}


@Preview
@Composable
fun DestinationCategoriesPreview() {
    DestinationCategories(categoryList = destinationCategories)
}