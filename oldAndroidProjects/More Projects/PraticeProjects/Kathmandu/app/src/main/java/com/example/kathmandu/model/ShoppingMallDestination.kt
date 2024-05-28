package com.example.kathmandu.model

import androidx.annotation.StringRes

data class ShoppingMallDestination(
    @StringRes val name: Int,
    @StringRes val intro: Int,
    @StringRes val description: Int?,
    @StringRes val highlights: Int,
    @StringRes val location: Int
)
