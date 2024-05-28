package com.example.kathmandu.model

import androidx.annotation.StringRes

data class MovieDestination(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val contact: Int,
    @StringRes val location: Int
)
