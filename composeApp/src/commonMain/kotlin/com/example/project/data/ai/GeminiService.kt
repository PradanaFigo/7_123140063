package com.example.project.data.ai

import com.example.project.platform.ApiConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class GeminiService(private val client: HttpClient) {
    suspend fun generateContent(prompt: String): Result<String> = runCatching {
        try {
            val request = GeminiRequest(
                contents = listOf(Content(parts = listOf(Part(text = prompt))))
            )

            val response: HttpResponse = client.post("https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent") {
                contentType(ContentType.Application.Json)
                parameter("key", ApiConfig.geminiApiKey)
                setBody(request)
            }

            if (response.status.isSuccess()) {
                val geminiResponse: GeminiResponse = response.body()
                geminiResponse.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
                    ?: throw Exception("Server merespon tapi tidak ada teks jawaban")
            } else {
                val errorDetail = response.bodyAsText()
                throw Exception("Error ${response.status.value}: $errorDetail")
            }
        } catch (e: Exception) {
            throw Exception(e.message ?: "Gagal memproses data")
        }
    }
}