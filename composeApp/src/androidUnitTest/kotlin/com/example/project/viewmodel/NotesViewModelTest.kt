package com.example.project.viewmodel

import app.cash.turbine.test
import com.example.project.data.NoteRepository
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)
class NotesViewModelTest {

    private val mockRepo = mockk<NoteRepository>(relaxed = true)
    private lateinit var viewModel: NoteViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        viewModel = NoteViewModel(mockRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `notes flow dapat diakses`() = runTest {
        viewModel.notes.test {
            val item = awaitItem()
            assertNotNull(item)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `toggleFavorite tidak crash`() = runTest {
        viewModel.toggleFavorite(1L)
    }

    @Test
    fun `deleteNote berjalan lancar`() = runTest {
        viewModel.deleteNote(1L)
    }

    @Test
    fun `addNote dipanggil dengan benar`() = runTest {
        viewModel.addNote("Judul Tugas", "Isi Tugas")
    }
}