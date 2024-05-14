package com.example.kathmandu.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    @StringRes val category: Int,
    @DrawableRes val imageRes: Int
)
