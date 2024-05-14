package com.example.notes.data

//data class NotesUiState(
//    val id: Int = 0,
//    val title: String = "",
//    val subTitle: String = "",
//    val body: String = ""
//)

data class NotesUiState(
    val noteDetails: NoteDetails = NoteDetails()
)


data class NoteDetails(
    val id: Int = 0,
    val title: String = "",
    val subTitle: String = "",
    val body: String = ""
)
