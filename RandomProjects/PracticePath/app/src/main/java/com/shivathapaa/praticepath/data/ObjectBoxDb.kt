package com.shivathapaa.praticepath.data

import android.content.Context
import io.objectbox.BoxStore
import io.objectbox.exception.DbException
import io.objectbox.exception.FileCorruptException

object ObjectBoxDb {
    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = try {
            MyObjectBox.builder()
                .androidContext(context.applicationContext)
                .build()
        } catch (e: DbException) {
            if (e.javaClass == DbException::class.java || e is FileCorruptException) {
                // Failed to build BoxStore due to database file issue, store message: Todo: try to notify user
//                dbExceptionMessage = e.toString()
                return
            } else {
                // Failed to build BoxStore due to developer error
                throw e
            }
        }
    }
}