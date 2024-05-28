package com.shivathapaa.praticepath.data

import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import io.objectbox.query.QueryBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NoteDao(boxStore: BoxStore) {

    private var noteBox: Box<Note> = boxStore.boxFor()  // reified type parameter unlike below line
    // or, private var noteBox: Box<Note> = boxStore.boxFor(Note::class.java)

    suspend fun insert(note: Note) {
        noteBox.put(note)
    }

    suspend fun delete(note: Note) {
        noteBox.remove(note)
    }

    suspend fun update(note: Note) {
        noteBox.put(note)
    }

    // using suspend function for Flow is not necessary as it does not block main thread (tara, why not make it obvious)

    suspend fun getNote(id: Long): Flow<Note> {
        return flowOf(noteBox.get(id))
    }

    suspend fun getAllNotes(): Flow<List<Note>> {
        return flowOf(noteBox.query().order(Note_.date, QueryBuilder.DESCENDING).build().find())
    }

}