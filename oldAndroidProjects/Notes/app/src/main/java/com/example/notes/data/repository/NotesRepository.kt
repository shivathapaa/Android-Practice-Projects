package com.example.notes.data.repository

import com.example.notes.data.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getNoteStream(id: Int): Flow<Note>

    fun getAllNotesStream(): Flow<List<Note>>

}