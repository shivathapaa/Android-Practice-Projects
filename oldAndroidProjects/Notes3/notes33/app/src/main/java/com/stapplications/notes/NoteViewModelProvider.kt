package com.stapplications.notes

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.stapplications.notes.search.SearchSViewModel
import com.stapplications.notes.presentation.home.HomeScreenViewModel
import com.stapplications.notes.presentation.screens.note.presentation.update.add.AddNoteViewModel
import com.stapplications.notes.presentation.screens.note.presentation.update.edit.EditNoteViewModel
import com.stapplications.notes.presentation.screens.todo.presentation.TodoScreenViewModel
import com.stapplications.notes.presentation.screens.todo.presentation.update.AddTodoViewModel

object NoteViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            HomeScreenViewModel(
                notesApplication().container.notesRepository,
                notesApplication().layoutPreferencesRepository,
                notesApplication().noteSSearchManager
            )
        }

        initializer {
            AddNoteViewModel(
                notesApplication().container.notesRepository
            )
        }

        initializer {
            EditNoteViewModel(
                this.createSavedStateHandle(),
                notesApplication().container.notesRepository
            )
        }

        initializer {
            SearchSViewModel(
                notesApplication().noteSSearchManager
            )
        }

        initializer {
            AddTodoViewModel(
                notesApplication().container.todoRepository
            )
        }

        initializer {
            TodoScreenViewModel(
                notesApplication().container.todoRepository
            )
        }
    }

}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [NotesApplication].
 */
fun CreationExtras.notesApplication(): NotesApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as NotesApplication)