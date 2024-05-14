package com.stapplications.notes.presentation.screens.note.data

import com.stapplications.notes.presentation.screens.note.data.Note

data class NoteUiState(
    val noteDetails: NoteDetails = NoteDetails(),
    val isUpdated: Boolean = false
)

data class NoteDetails(
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val dateTime: String = "",
    val category: String = "None",
    val imageUri: String? = null
)

// Extension function to convert [NoteDetails] to [Note]
fun NoteDetails.toNote(): Note = Note(
    id = id,
    title = title,
    body = body,
    dateTime = dateTime,
    category = category,
    imageUri = imageUri
)

// Extension function to convert [Note] to [NoteUiState]
fun Note.toNoteUiState(isUpdated: Boolean = true): NoteUiState = NoteUiState(
    noteDetails = this.toNoteDetails(),
    isUpdated = isUpdated
)

// Extension function to convert [Note] to [NoteDetails]
fun Note.toNoteDetails(): NoteDetails = NoteDetails(
    id = id,
    title = title,
    body = body,
    dateTime = dateTime,
    category = category,
    imageUri = imageUri
)