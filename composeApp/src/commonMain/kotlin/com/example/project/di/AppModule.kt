package com.example.project.di

import com.example.project.data.*
import com.example.project.data.ai.*
import com.example.project.platform.*
import com.example.project.viewmodel.*
import com.example.project.db.NotesDatabase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import com.russhwolf.settings.Settings

val appModule = module {
    // Database & Storage
    single { SettingsManager(get<Settings>()) }
    single { NotesDatabase(get()) }
    single { NoteRepository(get<NotesDatabase>()) }

    // Platform Services
    single { DeviceInfo() }
    single { NetworkMonitor() }
    single { BatteryInfo() }

    // Ktor & AI
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }
    single { GeminiService(get<HttpClient>()) }
    single<AiRepository> { AiRepositoryImpl(get<GeminiService>()) }

    // ViewModels
    single { NoteViewModel(get<NoteRepository>()) }
    single { SettingsViewModel(get<SettingsManager>()) }
    single { AiViewModel(get<AiRepository>()) }
}