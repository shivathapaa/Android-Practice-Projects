package com.example.kathmandu.model

import androidx.annotation.StringRes

data class ResortDestination(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val rating: Int,
    @StringRes val features: Int,
    @StringRes val location: Int
)