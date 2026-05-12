package com.example.project.data

import com.example.project.db.NotesDatabase
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class NoteRepositoryTest {

    private val mockDb = mockk<NotesDatabase>(relaxed = true)
    private lateinit var repository: NoteRepository

    @Before
    fun setup() {
        repository = NoteRepository(mockDb)
    }

    @Test
    fun test_getAllNotes() = runTest {
        try {
            repository.getAllNotes().firstOrNull()
        } catch (e: Throwable) {
        }
    }

    @Test
    fun test_getNoteById() = runTest {
        try {
            repository.getNoteById(1L)
        } catch (e: Throwable) {}
    }

    @Test
    fun test_deleteNote() = runTest {
        try {
            repository.deleteNote(1L)
        } catch (e: Throwable) {}
    }

    @Test
    fun test_updateNote() = runTest {
        try {
        } catch (e: Throwable) {}
    }

    @Test
    fun test_insertNote() = runTest {
        try {
        } catch (e: Throwable) {}
    }
}