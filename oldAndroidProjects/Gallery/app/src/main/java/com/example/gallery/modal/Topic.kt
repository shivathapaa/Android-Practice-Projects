package com.example.gallery.modal

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId: Int,
    val numberOfCourse: Int,
    @DrawableRes val drawableResourceId: Int
)
