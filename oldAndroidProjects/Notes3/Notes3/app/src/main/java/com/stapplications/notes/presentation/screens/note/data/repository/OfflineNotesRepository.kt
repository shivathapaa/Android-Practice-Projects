package com.stapplications.notes.presentation.screens.note.data.repository

import com.stapplications.notes.presentation.screens.note.data.Note
import com.stapplications.notes.presentation.screens.note.data.NoteDao
import kotlinx.coroutines.flow.Flow

class OfflineNotesRepository(private val noteDao: NoteDao) : NotesRepository {

    override fun getAllNotesStream(): Flow<List<Note>> = noteDao.getAllItems()

    override fun getNoteStream(id: Int): Flow<Note> = noteDao.getNote(id)

    override suspend fun insertNote(note: Note) = noteDao.insert(note)

    override suspend fun deleteNote(note: Note) = noteDao.delete(note)

    override suspend fun updateNote(note: Note) = noteDao.update(note)

    override suspend fun updateImageUri(id: Int, imageUri: String) =
        noteDao.updateImageUri(noteId = id, imageUri = imageUri)

}