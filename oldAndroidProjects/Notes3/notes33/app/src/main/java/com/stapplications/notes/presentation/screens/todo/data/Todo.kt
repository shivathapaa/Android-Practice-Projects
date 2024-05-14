package com.stapplications.notes.presentation.screens.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val todoId: Int,
    val title: String,
    val body: List<Pair<String, Boolean>>,
    val isDone: Boolean,
    val priority: Int,
    val reminder: String?
)
