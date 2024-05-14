package com.example.dailytipsforandroiddevelopers.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tips(
    @StringRes val title: Int,
    @StringRes val pointOne: Int,
    @StringRes val pointTwo: Int,
    @DrawableRes val imageResource: Int
)
