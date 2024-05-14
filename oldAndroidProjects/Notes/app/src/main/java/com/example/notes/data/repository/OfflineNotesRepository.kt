package com.example.notes.data.repository

import com.example.notes.data.Note
import com.example.notes.data.NoteDao
import kotlinx.coroutines.flow.Flow

class OfflineNotesRepository(private val notesDao: NoteDao) : NotesRepository {

    override suspend fun insertNote(note: Note) = notesDao.insert(note)

    override suspend fun updateNote(note: Note) = notesDao.update(note)

    override suspend fun deleteNote(note: Note) = notesDao.delete(note)

    override fun getNoteStream(id: Int): Flow<Note> = notesDao.getNote(id)

    override fun getAllNotesStream(): Flow<List<Note>> = notesDao.getAllNotes()

}