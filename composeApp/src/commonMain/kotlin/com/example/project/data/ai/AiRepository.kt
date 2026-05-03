package com.example.project.data.ai

interface AiRepository {
    suspend fun summarize(text: String): Result<String>
    suspend fun translate(text: String, targetLang: String): Result<String>
}

class AiRepositoryImpl(private val geminiService: GeminiService) : AiRepository {
    override suspend fun summarize(text: String): Result<String> {
        val prompt = """
            Rangkum teks berikut dalam 2-3 kalimat.
            Fokus pada poin-poin utama.
            Teks:
            $text
        """.trimIndent()
        return geminiService.generateContent(prompt)
    }

    override suspend fun translate(text: String, targetLang: String): Result<String> {
        val prompt = """
            Terjemahkan teks berikut ke bahasa $targetLang.
            Hanya berikan hasil terjemahannya saja, tanpa teks tambahan.
            Teks:
            $text
        """.trimIndent()
        return geminiService.generateContent(prompt)
    }
}