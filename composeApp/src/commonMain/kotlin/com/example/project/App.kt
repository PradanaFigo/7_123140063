package com.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.project.data.DatabaseDriverFactory
import com.example.project.data.NoteRepository
import com.example.project.data.SettingsManager
import com.example.project.db.NotesDatabase
import com.example.project.navigation.AppNavigation
import com.example.project.viewmodel.NoteViewModel
import com.example.project.viewmodel.SettingsViewModel
import com.russhwolf.settings.Settings // 🔥 Import ini wajib agar Settings() dikenali

@Composable
fun App(driverFactory: DatabaseDriverFactory) {
    MaterialTheme {
        val database = remember { NotesDatabase(driverFactory.createDriver()) }
        val repository = remember { NoteRepository(database) }
        val noteViewModel = remember { NoteViewModel(repository) }
        val settings = remember { Settings() }
        val settingsManager = remember { SettingsManager(settings) }
        val settingsViewModel = remember { SettingsViewModel(settingsManager) }

        AppNavigation(viewModel = noteViewModel, settingsViewModel = settingsViewModel)
    }
}