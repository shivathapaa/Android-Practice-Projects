package com.example.kathmandu.model

import androidx.annotation.StringRes

data class TempleDestination(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val details: Int?
)