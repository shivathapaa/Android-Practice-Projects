package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.BookInfo

@Composable
fun HomeScreen(
    bookshelfUiState: BookshelfUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (BookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(modifier = modifier.size(200.dp))
        is BookshelfUiState.Success -> BookLazyColumnScreen(
            bookInfo = bookshelfUiState.books,
            modifier = modifier
                .padding(
                    start = dimensionResource(R.dimen.medium_padding),
                    top = dimensionResource(R.dimen.medium_padding),
                    end = dimensionResource(R.dimen.medium_padding)
                ),
            contentPadding = contentPadding
        )
        is BookshelfUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = modifier)
    }
}

@Composable
fun BookLazyColumnScreen(
    bookInfo: List<BookInfo>,
    modifier: Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = bookInfo, key = { book -> book.title}) {bookInfo ->
            BookInfo(
                bookInfo = bookInfo,
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun BookInfo(
    bookInfo: BookInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row() {
            BookThumbnail(
                image = "",
                modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding))
            )
            BookIntroDetail(
                bookInfo = bookInfo,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.medium_padding),
                        bottom = dimensionResource(id = R.dimen.medium_padding),
                        end = dimensionResource(id = R.dimen.medium_padding)
                    )
            )
        }
    }
}

@Composable
fun BookIntroDetail(
    bookInfo: BookInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = bookInfo.author,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = bookInfo.title,
            style = MaterialTheme.typography.titleLarge
        )
        RatingStar(rating = bookInfo.rating)
        Text(
            text = bookInfo.category,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun RatingStar(
    rating: Double = 0.0,
    stars: Int = 5,
    starColor: Color = Color.Yellow,
    modifier: Modifier = Modifier
) {
    var isHalfStar: Boolean = (rating % 1) != 0.0

    Row(
        modifier = modifier
    ) {
        for (index in 1..stars) {
            Icon(
                tint = starColor,
                imageVector = if (index <= rating) {
                    Icons.Filled.Star
                } else {
                    if (isHalfStar) {
                        Icons.Outlined.Star //Half Star not available
                    } else {
                        Icons.Outlined.Star
                    }
                },
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(id = R.dimen.rating_star_size))
            )
        }
        Text(
            text = rating.toString(),
            modifier = Modifier
                .padding(start = dimensionResource(id = R.dimen.small_padding))
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun BookThumbnail(
    image: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.loading_img),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
    )
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading)
    )
}

@Preview(showBackground = true)
@Composable
fun BookIntroDetailPreview() {
    BookIntroDetail(
        bookInfo = com.example.bookshelf.model.BookInfo(
            title = "Pratice coding",
            thumbnail = "",
            author = "Kaji Kalu",
            category = "Education",
            rating = 1.0
        )
    )
}