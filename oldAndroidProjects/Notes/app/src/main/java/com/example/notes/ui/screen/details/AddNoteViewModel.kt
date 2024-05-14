package com.example.notes.ui.screen.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notes.data.Note
import com.example.notes.data.NoteDetails
import com.example.notes.data.NotesUiState
import com.example.notes.data.repository.NotesRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNoteViewModel(private val notesRepository: NotesRepository) : ViewModel() {
//    private val _noteDetailsUiState = MutableStateFlow(NotesUiState())
//    val notesUiState: StateFlow<NotesUiState> = _noteDetailsUiState.asStateFlow()

    var notesUiState by mutableStateOf(NotesUiState())
        private set

    var bodyWordCount by mutableIntStateOf(0)
        private set

    fun updateUiState(noteDetails: NoteDetails) {
        notesUiState = NotesUiState(noteDetails = noteDetails)
        bodyWordCount = countBodyCharacters(notesUiState.noteDetails.toNote().body)
    }

    suspend fun saveNote() {
        notesRepository.insertNote(notesUiState.noteDetails.toNote())
    }

}


fun NoteDetails.toNote(): Note = Note(
    id = id,
    title = title,
    subTitle = subTitle,
    body = body
)

fun Note.toNoteUiState(): NotesUiState = NotesUiState(
    noteDetails = this.toNoteDetails()
)

fun Note.toNoteDetails(): NoteDetails = NoteDetails(
    id = id,
    title = title,
    subTitle = subTitle,
    body = body
)

fun getFormattedTime(): String {
    val localDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("MMMM d h:mm a")

    return localDateTime.format(formatter)
}

fun countBodyCharacters(noteBody: String): Int {
    return noteBody.replace(" ", "").count()
}