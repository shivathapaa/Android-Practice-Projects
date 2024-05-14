package com.stapplications.notes.presentation.screens.note.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNote(id: Int): Flow<Note> // Room runs the query on the background thread

    @Query("SELECT * FROM notes ORDER BY dateTime DESC")
    fun getAllItems(): Flow<List<Note>>

    @Query("UPDATE notes SET imageUri = :imageUri WHERE id = :noteId")
    suspend fun updateImageUri(noteId: Int, imageUri: String)

}