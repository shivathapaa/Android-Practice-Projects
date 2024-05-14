package com.example.notes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// abstract class acts as the database holder
// it is defined abstract because Room creates the implementation for you/me ;)
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    // database class provides the instances of DAOs defined, so that app can use the dao to communicate with Db

    abstract fun noteDao(): NoteDao

    // create only one instance of database (define it as singleton)
    companion object {
/*The value of a volatile variable is never cached, and all reads and writes are to and from the main memory.
 These features help ensure the value of Instance is always up to date and is the same for all execution threads.
 It means that changes made by one thread to Instance are immediately visible to all other threads.*/

        @Volatile
        private var Instance: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            // synchronized -> only one thread of execution at a time can enter this block of code, (prevents race condition)
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NotesDatabase::class.java, "note_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}