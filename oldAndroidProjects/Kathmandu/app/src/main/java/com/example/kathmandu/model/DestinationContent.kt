package com.example.kathmandu.model

import androidx.annotation.StringRes

data class DestinationContent(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val time: Int?,
    @StringRes val location: Int?,
    @StringRes val contact: Int?,
    @StringRes val rating: Int?,
    @StringRes val features: Int?,
    @StringRes val exhibition: Int?,
    @StringRes val intro: Int?,
    @StringRes val highlights: Int?,
    @StringRes val details: Int?

)