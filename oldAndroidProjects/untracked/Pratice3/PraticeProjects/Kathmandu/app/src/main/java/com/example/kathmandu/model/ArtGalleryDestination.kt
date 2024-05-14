package com.example.kathmandu.model

import androidx.annotation.StringRes

data class ArtGalleryDestination(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val exhibition: Int,
    @StringRes val time: Int,
    @StringRes val location: Int
)