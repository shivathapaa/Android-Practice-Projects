package com.stapplications.notes.navigation

import androidx.annotation.StringRes
import com.stapplications.notes.R

const val NOTE_ID_ARGUMENT_KEY = "noteId"

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    data object Home : Screen("home", R.string.notes)
    data object AddNote : Screen("addNote", R.string.add_note)
    data object EditNote : Screen("editNote/{$NOTE_ID_ARGUMENT_KEY}", R.string.edit) {
        fun passId(noteId: Int): String {
            return this.route.replace(oldValue = "{$NOTE_ID_ARGUMENT_KEY}", newValue = noteId.toString())
        }
    }
}
