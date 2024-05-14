package com.stapplications.notes.search


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


data class NoteSListState(
    val notes: List<NoteS> = emptyList(),
    val searchQuery: String = ""
)

class SearchSViewModel(private val noteSSearchManager: NoteSSearchManager) : ViewModel() {

    var noteListState: NoteSListState by mutableStateOf(NoteSListState())
        private set

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            noteSSearchManager.init()
        }
    }

    // Directly used in the homeScreenViewModel for more flexibility
    /*    suspend fun addNote(notes: List<NoteS>): Boolean {
            return noteSSearchManager.addNote(notes)
        }

        suspend fun removeNote(namespace: String, id: String): Boolean {
            return noteSSearchManager.removeNote(namespace, id)
        }*/

    fun onSearchQueryChange(query: String): List<NoteS> {
        noteListState = noteListState.copy(searchQuery = query)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val notes = noteSSearchManager.searchNotes(query)
            noteListState = noteListState.copy(notes = notes)
        }
        return noteListState.notes
    }

    override fun onCleared() {
        noteSSearchManager.closeSession()
        super.onCleared()
    }

}