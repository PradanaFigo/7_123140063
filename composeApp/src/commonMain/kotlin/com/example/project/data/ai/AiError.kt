package com.example.project.data.ai

sealed class AiError : Exception() {
    data class NetworkError(override val message: String) : AiError()
    data class ServerError(override val message: String) : AiError()
    data class UnknownError(override val message: String) : AiError()
}