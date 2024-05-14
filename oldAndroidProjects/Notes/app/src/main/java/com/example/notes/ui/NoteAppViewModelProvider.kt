package com.example.notes.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.notes.NotesApplication
import com.example.notes.ui.screen.details.AddNoteViewModel
import com.example.notes.ui.screen.edit.EditNoteViewModel
import com.example.notes.ui.screen.home.HomeScreenViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object NoteAppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeScreenViewModel(
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
            AddNoteViewModel(
                notesApplication().container.notesRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [NotesApplication].
 */
fun CreationExtras.notesApplication() : NotesApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as NotesApplication)