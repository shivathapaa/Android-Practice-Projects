package com.stapplications.notes.presentation.screens.note.data.repository

import com.stapplications.notes.presentation.screens.note.data.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getAllNotesStream(): Flow<List<Note>>

    fun getNoteStream(id: Int): Flow<Note>

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun updateImageUri(id: Int, imageUri: String)

}