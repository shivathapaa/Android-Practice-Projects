package com.example.kathmandu.model

import androidx.annotation.StringRes

data class CasinoDestination(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val time: Int,
    @StringRes val location: Int,
    @StringRes val contact: Int
)
