package com.example.project.platform

import kotlinx.coroutines.flow.Flow

expect class NetworkMonitor() {
    fun observeConnectivity(): Flow<Boolean>
}