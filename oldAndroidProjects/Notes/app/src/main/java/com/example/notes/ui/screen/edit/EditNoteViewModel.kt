package com.example.notes.ui.screen.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NoteDetails
import com.example.notes.data.NotesUiState
import com.example.notes.data.repository.NotesRepository
import com.example.notes.ui.screen.details.countBodyCharacters
import com.example.notes.ui.screen.details.toNote
import com.example.notes.ui.screen.details.toNoteUiState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditNoteViewModel(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val noteId: Int = checkNotNull(savedStateHandle[EditNoteDestination.noteIdArg])

    var notesUiState by mutableStateOf(NotesUiState())
        private set

    var bodyCharacterCount by mutableIntStateOf(0)
        private set

    init {
        viewModelScope.launch {
            notesUiState = notesRepository.getNoteStream(noteId)
                .first()
                .toNoteUiState()
        }
    }

    fun updateUiState(noteDetails: NoteDetails) {
        notesUiState = NotesUiState(noteDetails = noteDetails)
        bodyCharacterCount = countBodyCharacters(noteDetails.body)
    }

    suspend fun saveUpdate() {
        notesRepository.updateNote(notesUiState.noteDetails.toNote())
    }
}

