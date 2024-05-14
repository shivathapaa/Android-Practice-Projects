package com.example.zplayer.ui.list

import com.example.zplayer.R

data class MusicFakeList(
    val image: Int,
    val heading: String,
    val supportingText: String,
)

val fakeList = listOf<MusicFakeList>(
    MusicFakeList(R.drawable.album_art, "Pohor Saal Khusi", "Aruna Lama"),
    MusicFakeList(R.drawable.album_art, "Pohor Saal Khusi", "Aruna Lama"),
    MusicFakeList(R.drawable.album_art, "Pohor Saal Khusi", "Aruna Lama"),
    MusicFakeList(R.drawable.album_art, "Pohor Saal Khusi", "Aruna Lama")
)