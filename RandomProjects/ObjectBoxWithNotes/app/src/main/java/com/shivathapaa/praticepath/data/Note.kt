package com.shivathapaa.praticepath.data

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Note(
    @Id var id: Long = 0,
    var title: String? = null,
    var body: String? = null,
    var date: String? = null
)