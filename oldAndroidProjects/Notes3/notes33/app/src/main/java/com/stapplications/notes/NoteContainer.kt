package com.stapplications.notes

import android.content.Context
import com.stapplications.notes.presentation.screens.note.data.NotesDatabase
import com.stapplications.notes.presentation.screens.note.data.repository.NotesRepository
import com.stapplications.notes.presentation.screens.note.data.repository.OfflineNotesRepository
import com.stapplications.notes.presentation.screens.todo.data.repository.OfflineTodoRepository
import com.stapplications.notes.presentation.screens.todo.data.repository.TodoRepository

// Container for Dependency Injection
interface NoteContainer {
    val notesRepository: NotesRepository
    val todoRepository: TodoRepository
}

// Provides the instance of [OfflineNotesRepository]
class NoteAppDataContainer(context: Context) : NoteContainer {

    /*Instantiate the database instance by calling getDatabase() on
     the InventoryDatabase class passing in the context
     and call .itemDao() to create the instance of Dao.*/
    override val notesRepository: NotesRepository by lazy {
        OfflineNotesRepository(NotesDatabase.getDatabase(context).noteDao())
    }
    override val todoRepository: TodoRepository by lazy {
        OfflineTodoRepository(NotesDatabase.getDatabase(context).todoDao())
    }
}