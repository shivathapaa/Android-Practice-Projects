package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//The Database class provides your app with instances of the DAOs you define.
// In turn, the app can use the DAOs to retrieve data from the database as instances of the associated data entity objects.
// The app can also use the defined data entities to update rows from the corresponding tables or to create new rows for insertion.

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    // Define an abstract method or property that returns an ItemDao instance, and the Room generates the implementation for you
    abstract fun itemDao(): ItemDao

    // companion object because we want to have only one instance of the database (singleton)
    companion object {

        //The value of a volatile variable is never cached, and all reads and writes are to and from the main memory.
        // These features help ensure the value of Instance is always up to date and is the same for all execution threads.
        // It means that changes made by one thread to Instance are immediately visible to all other threads.
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            // Wrapping the code to get the database inside a synchronized block means that only one thread of execution at a time can enter this block of code, which makes sure the database only gets initialized once.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}