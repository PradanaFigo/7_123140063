package com.example.project.data.ai

import com.example.project.platform.ApiConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class GeminiService(private val client: HttpClient) {
    suspend fun generateContent(prompt: String): Result<String> = runCatching {
        try {
            val request = GeminiRequest(
                contents = listOf(Content(parts = listOf(Part(text = prompt))))
            )

            val urlString = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=${ApiConfig.geminiApiKey}"

            val response: HttpResponse = client.post(urlString) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            if (response.status.isSuccess()) {
                val geminiResponse: GeminiResponse = response.body()
                geminiResponse.candidates.first().content.parts.first().text
            } else {
                throw Exception("Error Server: ${response.status.value}")
            }
        } catch (e: Exception) {
            throw Exception(e.message ?: "Gagal")
        }
    }
}