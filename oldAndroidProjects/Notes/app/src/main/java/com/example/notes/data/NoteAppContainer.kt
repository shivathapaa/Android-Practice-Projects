package com.example.notes.data

import android.content.Context
import com.example.notes.data.repository.NotesRepository
import com.example.notes.data.repository.OfflineNotesRepository

// Container for Dependency Injection
interface NoteAppContainer {
    val notesRepository: NotesRepository
}

// provides the instance of [OfflineNotesRepository]
class NoteAppDataContainer(context: Context) : NoteAppContainer {
    // Instantiate the database instance by calling getDatabase() on the InventoryDatabase class passing in the context and call .itemDao() to create the instance of Dao.
    override val notesRepository: NotesRepository by lazy {
        OfflineNotesRepository(NotesDatabase.getDatabase(context).noteDao())
    }

}