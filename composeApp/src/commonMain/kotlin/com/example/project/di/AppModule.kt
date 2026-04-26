package com.example.project.di

import com.example.project.data.*
import com.example.project.platform.*
import com.example.project.viewmodel.*
import com.example.project.db.NotesDatabase
import org.koin.dsl.module
import com.russhwolf.settings.Settings

val appModule = module {
    single { SettingsManager(get<Settings>()) }
    single { NotesDatabase(get()) }
    single { NoteRepository(get<NotesDatabase>()) }

    single { DeviceInfo() }
    single { NetworkMonitor() }
    single { BatteryInfo() }

    single { NoteViewModel(get<NoteRepository>()) }
    single { SettingsViewModel(get<SettingsManager>()) }
}