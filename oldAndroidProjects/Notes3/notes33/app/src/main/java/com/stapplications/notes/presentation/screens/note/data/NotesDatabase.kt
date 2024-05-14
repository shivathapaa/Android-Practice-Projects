package com.stapplications.notes.presentation.screens.note.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stapplications.notes.presentation.screens.todo.data.Converters
import com.stapplications.notes.presentation.screens.todo.data.Todo
import com.stapplications.notes.presentation.screens.todo.data.TodoDao

@Database(
    entities = [Note::class, Todo::class],
    version = 2,
    exportSchema = true,
//    autoMigrations = [AutoMigration(from = 1, to = 2)]
)
@TypeConverters(Converters::class)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun todoDao(): TodoDao

    companion object {

        @Volatile
        private var Instance: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = NotesDatabase::class.java,
                    name = "notes_database"
                )
//                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}