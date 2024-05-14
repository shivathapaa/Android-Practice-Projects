package com.stapplications.notes.presentation.screens.note.presentation.update.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.stapplications.notes.presentation.screens.note.data.NoteDetails
import com.stapplications.notes.presentation.screens.note.data.NoteUiState
import com.stapplications.notes.presentation.screens.note.data.repository.NotesRepository
import com.stapplications.notes.presentation.screens.note.data.toNote
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNoteViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    var noteUiState: NoteUiState by mutableStateOf(NoteUiState())
        private set

    var bodyWordCount by mutableIntStateOf(0)
        private set

    private val localDateTime: LocalDateTime = LocalDateTime.now()

    // call from coroutine
    fun updateUiState(noteDetails: NoteDetails) {
        noteUiState = NoteUiState(noteDetails = noteDetails, isUpdated = true)
        bodyWordCount = countBodyCharacters(noteUiState.noteDetails.toNote().body)
    }

    suspend fun saveNote() {
        val updatedNoteDetails = noteUiState.noteDetails.copy(dateTime = localDateTime.toString())
        noteUiState = noteUiState.copy(noteDetails = updatedNoteDetails)
        notesRepository.insertNote(noteUiState.noteDetails.toNote())
    }

    fun getFormattedTime(): String {
        val formatter = DateTimeFormatter.ofPattern("MMM d h:mm a")
        return localDateTime.format(formatter)
    }
}


fun countBodyCharacters(noteBody: String): Int {
    return noteBody.count { !it.isWhitespace() }
}

