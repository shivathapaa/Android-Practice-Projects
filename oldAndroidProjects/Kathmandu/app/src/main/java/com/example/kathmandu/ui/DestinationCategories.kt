package com.example.kathmandu.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kathmandu.R
import com.example.kathmandu.model.Category
import com.example.kathmandu.data.local.Categories.destinationCategories

@Composable
fun DestinationCategories(
    categoryList: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn() {
        items(categoryList) {
            CategoryCard(
                category = it.category,
                imageRes = it.imageRes
            )
        }
    }
}

@Composable
fun CategoryCard(
    @DrawableRes imageRes: Int,
    @StringRes category: Int,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.large_padding))
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.image_height))
                .fillMaxWidth()
                .blur(
                    radiusX = 3.dp,
                    radiusY = 3.dp,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                )
        )
        Text(
            text = stringResource(id = category),
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}


@Preview
@Composable
fun DestinationCategoriesPreview() {
    DestinationCategories(categoryList = destinationCategories)
}