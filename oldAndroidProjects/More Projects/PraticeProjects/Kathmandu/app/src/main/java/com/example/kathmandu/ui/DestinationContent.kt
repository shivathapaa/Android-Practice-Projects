package com.example.kathmandu.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.kathmandu.R


@Composable
fun DestinationDisplayCard(
    @DrawableRes imageRes: Int,
    @StringRes name: Int,
    @StringRes description: Int,
    @StringRes location: Int? = null,
    @StringRes contact: Int? = null,
    @StringRes rating: Int? = null,
    onDestinationClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.large_padding))
            .padding(top = dimensionResource(id = R.dimen.large_padding))
            .clickable(
                enabled = true,
                onClick = onDestinationClicked
            )
            .wrapContentSize()
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.large_padding))
        ) {
            DestinationImage(
                imageRes = imageRes,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.medium_padding))
            )
            TitleText(
                name = name,
                rating = rating,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.small_padding))
            )
            Text(
                text = stringResource(id = description),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.medium_padding))
            )
            if (location != null) {
                ExtraContentsIfNotNull(titleHead = R.string.location, content = location)
            }
            if (contact != null) {
                ExtraContentsIfNotNull(titleHead = R.string.contact, content = contact)
            }
        }
    }
}

@Composable
fun DestinationImage(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.image_height))
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
fun TitleText(
    @StringRes name: Int,
    @StringRes rating: Int? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.titleSmall,
            maxLines = 3,
            overflow = TextOverflow.Clip,
            textAlign = TextAlign.Start
        )
        if (rating != null) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = rating),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun ExtraContentsIfNotNull(
    @StringRes titleHead: Int,
    @StringRes content: Int
) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                append(stringResource(id = titleHead) + " ")
            }
            append(stringResource(id = content))
        },
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.secondary,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.small_padding))
    )
}

@Preview(showBackground = true)
@Composable
fun DestinationDisplayCardPreview() {
    DestinationDisplayCard(
        imageRes = R.drawable.talbarahi_temple,
        name = R.string.talbarahi_temple,
        description = R.string.talbarahi_temple_description,
        rating = R.string.gokarna_forest_resort_rating,
        location = R.string.ballyS_casino_location,
        contact = R.string.ballyS_casino_contact,
    )
}