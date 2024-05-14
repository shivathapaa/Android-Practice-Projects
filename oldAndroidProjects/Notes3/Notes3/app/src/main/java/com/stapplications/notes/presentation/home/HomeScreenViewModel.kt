package com.stapplications.notes.presentation.home

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stapplications.notes.R
import com.stapplications.notes.presentation.screens.note.data.Note
import com.stapplications.notes.presentation.screens.note.data.repository.LayoutPreferenceRepository
import com.stapplications.notes.presentation.screens.note.data.repository.NotesRepository
import com.stapplications.notes.search.NoteS
import com.stapplications.notes.search.NoteSSearchManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class HomeScreenViewModel(
    private val notesRepository: NotesRepository,
    private val layoutPreferenceRepository: LayoutPreferenceRepository,
    private val noteSSearchManager: NoteSSearchManager
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        viewModelScope.launch {
            noteSSearchManager.init()
        }
        // Fetch data from Room database on initialization
        viewModelScope.launch {
            notesRepository.getAllNotesStream().collect { notes ->
                _homeUiState.update { currentState ->
                    currentState.copy(noteList = notes)
                }
            }
        }
    }

    suspend fun addImageUri(context: Context, imageUri: Uri?, noteId: Int) {
        var savedUri: Uri? = null
        viewModelScope.launch {
            if (imageUri != null) {
                savedUri = storeImage(context, imageUri, noteId)
            }
            notesRepository.updateImageUri(noteId, savedUri.toString())
        }
    }

    suspend fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepository.deleteNote(note)
        noteSSearchManager.removeNote(NOTES_NAMESPACE, note.id.toString()) // search document
    }

    // Search Document

    suspend fun addNoteToDocument(notes: List<Note> = _homeUiState.value.noteList): Boolean {
        return noteSSearchManager.addNote(notes.map { it.toNoteS() })
    }

    // Layout

    // convert cold flow to hot stateFlow using stateIn()
    val layoutUiState: StateFlow<LayoutUiState> =
        layoutPreferenceRepository.isLinearLayout
            .map { isLinearLayout ->
                LayoutUiState(isLinearLayout)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = LayoutUiState()
            )

    fun selectLayout(isLinearLayout: Boolean) {
        viewModelScope.launch {
            layoutPreferenceRepository.saveLayoutPreference(isLinearLayout)
        }
    }

    // Image

    private suspend fun storeImage(context: Context, imageUri: Uri, noteId: Int): Uri? {
        // Open an InputStream from the content URI
        val inputStream = context.contentResolver.openInputStream(imageUri)
        /*
                // Not much suitable for this case
                val directory = context.cacheDir
        */
        val imageDir = context.getDir("images", Context.MODE_PRIVATE) // Dedicated dir

        val filename =
            "image_$noteId.jpg" // Or generate a unique name (I want to replace for the images of same noteId.)

        // File object for the destination file
        val destinationFile = File(imageDir, filename)

        // Open an OutputStream to write the image data
        val outputStream = withContext(Dispatchers.IO) {
            FileOutputStream(destinationFile)
        }

        var savedUri: Uri? = null

        try {
            inputStream?.copyTo(outputStream)
            savedUri = Uri.fromFile(destinationFile)
        } catch (e: Exception) {
            e.printStackTrace() // Errors during copying
        } finally {
            // Close the streams (for resource management)
            withContext(Dispatchers.IO) {
                inputStream?.close()
                outputStream.close()
            }
        }

        return savedUri
    }
}

data class HomeUiState(val noteList: List<Note> = listOf())
//data class HomeUiState(val noteList: List<Note> = listOf(), val selectedImageUri: Uri? = null)

data class LayoutUiState(
    val isLinearLayout: Boolean = true,
    val toggleIcon: Int =
        if (isLinearLayout) R.drawable.grid_layout else R.drawable.list_layout,
    val toggleContentDescription: Int =
        if (isLinearLayout) R.string.grid_layout else R.string.list_layout
)

const val NOTES_NAMESPACE = "all_notes"

fun Note.toNoteS(): NoteS = NoteS(
    namespace = NOTES_NAMESPACE,
    id = id.toString(),
    score = 1,
    title = title,
    body = body,
    category = category,
    dateTime = dateTime,
    imageUri = imageUri
)

fun NoteS.toNote(): Note = Note(
    id = id.toInt(),
    title = title,
    body = body,
    dateTime = dateTime,
    category = category,
    imageUri = imageUri
)

fun getFormattedTime(localDateTime: String): String {

    val formatterTime = DateTimeFormatter.ofPattern("h:mm a")
    val formatterDay = DateTimeFormatter.ofPattern("E h:mm a")
    val formatterWeek = DateTimeFormatter.ofPattern("MMMM d")
    val formatterYear = DateTimeFormatter.ofPattern("MMMM d, yyyy")

    val dateTime = LocalDateTime.parse(localDateTime)
    val now = LocalDateTime.now()
    val yesterday = now.minusDays(1).toLocalDate()

    return when {
        dateTime.toLocalDate() == now.toLocalDate() -> dateTime.format(formatterTime)
        dateTime.toLocalDate() == yesterday -> "Yesterday"
        ChronoUnit.DAYS.between(dateTime, now) >= 7 -> dateTime.format(formatterWeek)
        ChronoUnit.YEARS.between(dateTime, now) >= 1 -> dateTime.format(formatterYear)
        else -> dateTime.format(formatterDay)
    }
}

/*
REDUNDANT CODE

class HomeScreenViewModel(
    private val notesRepository: NotesRepository,
    private val layoutPreferenceRepository: LayoutPreferenceRepository,
    private val noteSSearchManager: NoteSSearchManager
) :
    ViewModel() {

    // Using MutableStateFlow for updatability (however, I want to directly update uri to the db, so not used!)
    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()


//    init {
//        // Fetch data from Room database on initialization
//        viewModelScope.launch {
//            notesRepository.getAllNotesStream().collect { notes ->
//                _homeUiState.update { currentState ->
//                    currentState.copy(noteList = notes)
//                }
//            }
//        }
//    }


    init {
        viewModelScope.launch {
            Log.d("CheckingMy1", "_homeUiState.value.noteList.size.toString()")

            // Fetch data from Room database on initialization
            notesRepository.getAllNotesStream().collect { notes ->
                _homeUiState.update { currentState ->
                    currentState.copy(noteList = notes)
                }
            }
            Log.d("CheckingMy2", "_homeUiState.value.noteList.size.toString()")
            noteSSearchManager.init()
//            val notes = (1..100).map {
//                NoteS(
//                    namespace = "all_notes",
//                    id = UUID.randomUUID().toString(),
//                    score = 1,
//                    title = "Note $it",
//                    body = "Body $it",
//                    category = "Category $it",
//                    dateTime = LocalDateTime.now().toString()
//                )
//            }
            Log.d("CheckingMy", _homeUiState.value.noteList.size.toString())
            noteSSearchManager.putNotes(_homeUiState.value.noteList.map { it.toNoteS() })
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

//    var homeUiState: StateFlow<HomeUiState> =
//        notesRepository.getAllNotesStream().map { HomeUiState(it) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = HomeUiState()
//            )

    suspend fun addImageUri(context: Context, imageUri: Uri?, noteId: Int) {
        var savedUri: Uri? = null
        viewModelScope.launch {
            if (imageUri != null) {
                savedUri = storeImage(context, imageUri, noteId)
            }
            notesRepository.updateImageUri(noteId, savedUri.toString())
        }
        /*
        // To update the image
        viewModelScope.launch {
            _homeUiState.update { currentState ->
                currentState.copy(selectedImageUri = imageUri)
            }
        }
        */
    }

    suspend fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepository.deleteNote(note)
    }

    // convert cold flow to hot stateFlow using stateIn()
    val layoutUiState: StateFlow<LayoutUiState> =
        layoutPreferenceRepository.isLinearLayout
            .map { isLinearLayout ->
                LayoutUiState(isLinearLayout)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = LayoutUiState()
            )

    fun selectLayout(isLinearLayout: Boolean) {
        viewModelScope.launch {
            layoutPreferenceRepository.saveLayoutPreference(isLinearLayout)
        }
    }

    private suspend fun storeImage(context: Context, imageUri: Uri, noteId: Int): Uri? {
        // Open an InputStream from the content URI
        val inputStream = context.contentResolver.openInputStream(imageUri)
        /*
                // Not much suitable for this case
                val directory = context.cacheDir
        */
        val imageDir = context.getDir("images", Context.MODE_PRIVATE) // Dedicated dir

        val filename =
            "image_$noteId.jpg" // Or generate a unique name (I want to replace for the images of same noteId.)

        // File object for the destination file
        val destinationFile = File(imageDir, filename)

        // Open an OutputStream to write the image data
        val outputStream = withContext(Dispatchers.IO) {
            FileOutputStream(destinationFile)
        }

        var savedUri: Uri? = null

        try {
            inputStream?.copyTo(outputStream)
            savedUri = Uri.fromFile(destinationFile)
        } catch (e: Exception) {
            e.printStackTrace() // Errors during copying
        } finally {
            // Close the streams (for resource management)
            withContext(Dispatchers.IO) {
                inputStream?.close()
                outputStream.close()
            }
        }

        return savedUri
    }


    var noteSearchState by mutableStateOf(NoteSListState())
        private set

    private var searchJob: Job? = null


    fun onSearchQueryChange(query: String) {
        noteSearchState = noteSearchState.copy(searchQuery = query)

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(20L)
            val notes = noteSSearchManager.searchNotes(query)
            noteSearchState = noteSearchState.copy(noteS = notes)
        }
    }

    override fun onCleared() {
        noteSSearchManager.closeSession()
        super.onCleared()
    }

}

data class HomeUiState(val noteList: List<Note> = listOf())
//data class HomeUiState(val noteList: List<Note> = listOf(), val selectedImageUri: Uri? = null)

data class LayoutUiState(
    val isLinearLayout: Boolean = true,
    val toggleIcon: Int =
        if (isLinearLayout) R.drawable.grid_layout else R.drawable.list_layout,
    val toggleContentDescription: Int =
        if (isLinearLayout) R.string.grid_layout else R.string.list_layout
)

data class NoteSListState(
    val noteS: List<NoteS> = emptyList(),
    val searchQuery: String = ""
)

fun Note.toNoteS(): NoteS = NoteS(
    namespace = "all_notes",
    id = id.toString(),
    score = 1,
    title = title,
    body = body,
    category = category,
    dateTime = dateTime
)

fun getFormattedTime(localDateTime: String): String {

    val formatterTime = DateTimeFormatter.ofPattern("h:mm a")
    val formatterDay = DateTimeFormatter.ofPattern("E h:mm a")
    val formatterWeek = DateTimeFormatter.ofPattern("MMMM d")
    val formatterYear = DateTimeFormatter.ofPattern("MMMM d, yyyy")

    val dateTime = LocalDateTime.parse(localDateTime)
    val now = LocalDateTime.now()
    val yesterday = now.minusDays(1).toLocalDate()

    return when {
        dateTime.toLocalDate() == now.toLocalDate() -> dateTime.format(formatterTime)
        dateTime.toLocalDate() == yesterday -> "Yesterday"
        ChronoUnit.DAYS.between(dateTime, now) >= 7 -> dateTime.format(formatterWeek)
        ChronoUnit.YEARS.between(dateTime, now) >= 1 -> dateTime.format(formatterYear)
        else -> dateTime.format(formatterDay)
    }
}
*/
