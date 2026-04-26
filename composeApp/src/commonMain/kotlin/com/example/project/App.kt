package com.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.project.data.DatabaseDriverFactory
import com.example.project.navigation.AppNavigation
import com.example.project.viewmodel.NoteViewModel
import com.example.project.viewmodel.SettingsViewModel
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
fun App(driverFactory: DatabaseDriverFactory) {
    KoinContext {
        MaterialTheme {
            val noteViewModel = koinInject<NoteViewModel>()
            val settingsViewModel = koinInject<SettingsViewModel>()

            AppNavigation(viewModel = noteViewModel, settingsViewModel = settingsViewModel)
        }
    }
}