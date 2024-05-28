package com.shivathapa.khaata.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shivathapa.khaata.data.dao.CategoryDao
import com.shivathapa.khaata.data.dao.ExpenseDao

@Database(version = 2, entities = [Category::class, Expense::class], exportSchema = false)
abstract class KhaataDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    abstract fun categoryDao(): CategoryDao

    //  allows access to the methods to create or get the database and uses the class name as the qualifier.
    companion object {
        @Volatile
        private var Instance: KhaataDatabase? = null

        fun getDatabase(context: Context): KhaataDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context = context, klass = KhaataDatabase::class.java, name = "expense_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}