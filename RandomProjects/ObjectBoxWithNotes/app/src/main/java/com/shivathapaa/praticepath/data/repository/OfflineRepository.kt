package com.shivathapaa.praticepath.data.repository

import com.shivathapaa.praticepath.data.Note
import com.shivathapaa.praticepath.data.NoteDao
import kotlinx.coroutines.flow.Flow

class OfflineRepository(private val notesDao: NoteDao) : NoteRepository {

    override suspend fun insertNote(note: Note) {
        notesDao.insert(note)
    }

    override suspend fun updateNote(note: Note) {
        notesDao.update(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.delete(note)
    }

    override suspend fun getNoteStream(id: Long): Flow<Note> {
        return notesDao.getNote(id)
    }

    override suspend fun getAllNotesStream(): Flow<List<Note>> {
        return notesDao.getAllNotes()
    }

}
/*
    // for later, we can simplify it as

    override suspend fun insertNote(note: Note) = notesDao.insert(note)

    override suspend fun updateNote(note: Note) = notesDao.update(note)

    override suspend fun deleteNote(note: Note) = notesDao.delete(note)

    override suspend fun getNoteStream(id: Long): Flow<Note> = notesDao.getNote(id)

    override suspend fun getAllNotesStream(): Flow<List<Note>> = notesDao.getAllNotes()

 */