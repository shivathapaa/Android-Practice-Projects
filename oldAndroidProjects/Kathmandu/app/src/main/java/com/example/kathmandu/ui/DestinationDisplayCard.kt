package com.example.kathmandu.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kathmandu.R


@Composable
fun DestinationDisplayCard(
    @DrawableRes imageRes: Int,
    @StringRes name: Int,
    @StringRes description: Int,
    @StringRes rating: Int?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.large_padding))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            DestinationImage(imageRes = imageRes, modifier = Modifier.fillMaxWidth())
            TitleText(name = name, rating = rating)
            Text(
                text = stringResource(id = description),
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun DestinationImage(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.medium_padding))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.medium_padding)))
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.image_height))

        )
    }
}

@Composable
fun TitleText(
    @StringRes name: Int,
    @StringRes rating: Int?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = name),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.small_padding))

        )
        if (rating != null) {
            Text(
                text = stringResource(id = rating),
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.small_padding))
            )
        }
    }
}

@Preview
@Composable
fun DestinationDisplayCardPreview() {
    DestinationDisplayCard(imageRes = R.drawable.talbarahi_temple, name = R.string.talbarahi_temple, description = R.string.talbarahi_temple_description, rating = R.string.taj_riverside_resort_rating)
}