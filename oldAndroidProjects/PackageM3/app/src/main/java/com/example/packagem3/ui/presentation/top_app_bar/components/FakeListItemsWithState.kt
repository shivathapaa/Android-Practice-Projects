package com.example.packagem3.ui.presentation.top_app_bar.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.packagem3.R

@Composable
fun FakeListItemsWithState(
    paddingValues: PaddingValues,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = paddingValues,
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_padding)),
        modifier = modifier
    ) {
        val list = List(100) { "Let's count $it" }
        items(count = list.size) {
            Text(
                list[it],
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.medium_padding))
            )
        }
    }
}
