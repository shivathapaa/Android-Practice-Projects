package com.stapplications.notes.presentation.screens.note.presentation.update.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stapplications.notes.navigation.NOTE_ID_ARGUMENT_KEY
import com.stapplications.notes.presentation.screens.note.data.NoteDetails
import com.stapplications.notes.presentation.screens.note.data.NoteUiState
import com.stapplications.notes.presentation.screens.note.data.repository.NotesRepository
import com.stapplications.notes.presentation.screens.note.data.toNote
import com.stapplications.notes.presentation.screens.note.data.toNoteUiState
import com.stapplications.notes.presentation.screens.note.presentation.update.add.countBodyCharacters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class EditNoteViewModel(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val noteId: Int = checkNotNull(savedStateHandle[NOTE_ID_ARGUMENT_KEY])

    private var localDateTime: String = LocalDateTime.now().toString()
    private var now: String = LocalDateTime.now().toString()

    var noteUiState: NoteUiState by mutableStateOf(NoteUiState())
        private set

    var bodyWordCount: Int by mutableIntStateOf(0)
        private set

    init {
        viewModelScope.launch {
            noteUiState = notesRepository.getNoteStream(noteId)
                .first()
                .toNoteUiState()

            if (!noteUiState.isUpdated || localDateTime.isNotBlank()) {
                localDateTime = noteUiState.noteDetails.dateTime
            }

            bodyWordCount = countBodyCharacters(noteUiState.noteDetails.toNote().body)
        }
    }

    fun updateUiState(noteDetails: NoteDetails) {
        noteUiState = NoteUiState(noteDetails = noteDetails.copy(dateTime = now), isUpdated = true)
        bodyWordCount = countBodyCharacters(noteUiState.noteDetails.toNote().body)
    }

    suspend fun saveNote() {
        notesRepository.updateNote(noteUiState.noteDetails.toNote())
    }

    suspend fun deleteNote() {
        notesRepository.deleteNote(noteUiState.noteDetails.toNote())
    }

    fun getFormattedTime(): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM d h:mm a")
        val formatterYear = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a")

        val dateTime = LocalDateTime.parse(localDateTime)
        val now = LocalDateTime.now()

        return when {
            ChronoUnit.YEARS.between(dateTime, now) >= 1 -> dateTime.format(formatterYear)
            else -> dateTime.format(formatter)
        }
    }
}