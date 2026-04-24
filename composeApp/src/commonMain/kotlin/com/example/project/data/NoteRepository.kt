package com.example.project.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import com.example.project.db.Note
import com.example.project.db.NotesDatabase

class NoteRepository(database: NotesDatabase) {
    private val queries = database.noteQueries

    fun getAllNotes(): Flow<List<Note>> = queries.selectAll().asFlow().mapToList(Dispatchers.IO)

    fun getNoteById(id: Long): Note? = queries.selectById(id).executeAsOneOrNull()

    suspend fun insertNote(title: String, content: String) {
        val now = 123456789L
        queries.insert(title, content, 0L, now)
    }

    suspend fun updateNote(id: Long, title: String, content: String, isFavorite: Boolean) {
        val favStatus = if (isFavorite) 1L else 0L
        queries.update(title, content, favStatus, id)
    }

    suspend fun deleteNote(id: Long) = queries.delete(id)
    suspend fun toggleFavorite(id: Long) {
        val note = getNoteById(id)
        if (note != null) {
            val newFavStatus = if (note.isFavorite == 1L) 0L else 1L
            queries.update(note.title, note.content, newFavStatus, id)
        }
    }
}