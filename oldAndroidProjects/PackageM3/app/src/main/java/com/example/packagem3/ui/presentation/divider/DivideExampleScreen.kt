package com.example.packagem3.ui.presentation.divider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.example.packagem3.R

@Composable
fun DividerExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
        ) {
            Text(text = "Default")
            HorizontalDivider()
            Text(text = "Hairline")
            HorizontalDivider(thickness = Dp.Hairline)
            Text(text = "Infinity")
            HorizontalDivider(thickness = Dp.Infinity)
            Text(text = "Unspecified")
            HorizontalDivider(thickness = Dp.Unspecified)
            Text(text = "8 Dp")
            HorizontalDivider(thickness = dimensionResource(id = R.dimen.extra_small_padding))
            Text(text = "12 Dp")
            HorizontalDivider(thickness = dimensionResource(id = R.dimen.small_padding))
            Text(text = "16 Dp")
            HorizontalDivider(thickness = dimensionResource(id = R.dimen.medium_padding))
        }
        
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.extra_large_padding)))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Default")
            VerticalDivider()
            Text(text = "Hairline")
            VerticalDivider(thickness = Dp.Hairline)
            Text(text = "Infinity")
            VerticalDivider(thickness = Dp.Infinity)
            Text(text = "Unspecified")
            VerticalDivider(thickness = Dp.Unspecified)
            Text(text = "8 Dp")
            VerticalDivider(thickness = dimensionResource(id = R.dimen.extra_small_padding))
            Text(text = "12 Dp")
            VerticalDivider(thickness = dimensionResource(id = R.dimen.small_padding))
            Text(text = "16 Dp")
            VerticalDivider(thickness = dimensionResource(id = R.dimen.medium_padding))
        }
    }
}
