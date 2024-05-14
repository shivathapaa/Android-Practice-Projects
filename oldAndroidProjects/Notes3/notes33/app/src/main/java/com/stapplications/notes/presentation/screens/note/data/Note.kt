package com.stapplications.notes.presentation.screens.note.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val body: String,
    val dateTime: String,
    val category: String,
    val imageUri: String?
)