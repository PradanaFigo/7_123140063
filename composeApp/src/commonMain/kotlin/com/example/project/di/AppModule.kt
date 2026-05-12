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

val dataModule = module {
    single { SettingsManager(get<Settings>()) }
    single { NotesDatabase(get()) }
    single { NoteRepository(get<NotesDatabase>()) }
}

val networkModule = module {
    single { DeviceInfo() }
    single { NetworkMonitor() }
    single { BatteryInfo() }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }
    single { GeminiService(get<HttpClient>()) }
    single<AiRepository> { AiRepositoryImpl(get<GeminiService>()) }
}

val viewModelModule = module {
    single { NoteViewModel(get<NoteRepository>()) }
    single { SettingsViewModel(get<SettingsManager>()) }
    single { AiViewModel(get<AiRepository>()) }
}

val appModule = listOf(
    dataModule,
    networkModule,
    viewModelModule
)