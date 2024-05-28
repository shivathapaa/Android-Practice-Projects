package com.example.kathmandu.data.local

import com.example.kathmandu.R
import com.example.kathmandu.model.Category

object Categories {
    val destinationCategories = listOf<Category>(
        Category(
            category = R.string.temples,
            imageRes = R.drawable.talbarahi_temple
        ),
        Category(
            category = R.string.art_gallery,
            imageRes = R.drawable.artudio_art_gallery
        ),
        Category(
            category = R.string.movie_threatre,
            imageRes = R.drawable.big_movies
        ),
        Category(
            category = R.string.shopping_malls,
            imageRes = R.drawable.sherpa_mall
        ),
        Category(
            category = R.string.resorts,
            imageRes = R.drawable.gokarna_forest_resort
        ),
        Category(
            category = R.string.casinos,
            imageRes = R.drawable.casinos
        )
    )
}