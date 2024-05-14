package com.stapplications.notes.search

import android.content.Context
import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.PutDocumentsRequest
import androidx.appsearch.app.RemoveByDocumentIdRequest
import androidx.appsearch.app.SearchSpec
import androidx.appsearch.app.SetSchemaRequest
import androidx.appsearch.localstorage.LocalStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteSSearchManager(
    private val context: Context
) {
    private var appSearchSession: AppSearchSession? = null

    suspend fun init() {
        withContext(Dispatchers.IO) {
            val localStorageSession = LocalStorage.createSearchSessionAsync(
                LocalStorage.SearchContext.Builder(context, DATABASE_NAME).build()
            )

            // Sets the schema for the AppSearch database by registering the [NoteS] document class as a
            // schema type in the overall database schema.
            val setSchemaRequest =
                SetSchemaRequest.Builder().addDocumentClasses(NoteS::class.java).build()
            appSearchSession = localStorageSession.get()
            appSearchSession?.setSchemaAsync(setSchemaRequest)
        }
    }

    suspend fun addNote(noteS: List<NoteS>): Boolean {
        val request = PutDocumentsRequest.Builder().addDocuments(noteS).build()
        return withContext(Dispatchers.IO) {
            appSearchSession?.putAsync(request)?.get()?.isSuccess == true
        }
    }

    suspend fun removeNote(nameSpace: String, id: String): Boolean {
        val request = RemoveByDocumentIdRequest.Builder(nameSpace).addIds(id).build()
        return withContext(Dispatchers.IO) {
            appSearchSession?.removeAsync(request)?.get()?.isSuccess == true
        }
    }

    suspend fun searchNotes(query: String): List<NoteS> {
        return withContext(Dispatchers.IO) {
            val searchSpec = SearchSpec.Builder()
                .setRankingStrategy(SearchSpec.RANKING_STRATEGY_CREATION_TIMESTAMP)
                .setOrder(SearchSpec.ORDER_DESCENDING)
//                .setSnippetCount(25)
                .build()

            val searchResults =
                appSearchSession?.search(query, searchSpec) ?: return@withContext emptyList()

            val page = searchResults.nextPageAsync.get()

//            return@withContext searchResults.nextPageAsync.get()

            page.mapNotNull { searchResult ->
                if (searchResult.genericDocument.schemaType == NoteS::class.java.simpleName) {
                    if (query.isNotBlank()) {
                        searchResult.getDocument(NoteS::class.java)
                    } else null // to skip for ""
                } else null
            }
        }
    }

    fun closeSession() {
        appSearchSession?.close()
        appSearchSession = null
    }

    companion object {
        private const val DATABASE_NAME = "notesDatabase"
    }
}