package com.stapplications.notes.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.stapplications.notes.R

@Composable
fun AddSaveFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    extended: Boolean,
    text: String,
    @DrawableRes icon: Int,
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.medium_padding))
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = text)
            // Toggle the visibility of the content with animation.
            AnimatedVisibility(visible = extended) {
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.extra_small_padding),
                            top = dimensionResource(id = R.dimen.fab_extend_text_top_padding)
                        )
                )
            }
        }
    }
}