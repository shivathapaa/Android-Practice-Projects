package com.shivathapaa.praticepath.data.repository

import com.shivathapaa.praticepath.data.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteStream(id: Long): Flow<Note>

    suspend fun getAllNotesStream(): Flow<List<Note>>

}