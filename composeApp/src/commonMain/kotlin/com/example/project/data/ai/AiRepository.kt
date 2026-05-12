package com.example.project.data.ai

interface AiRepository {
    suspend fun summarize(text: String): Result<String>
    suspend fun translate(text: String, targetLang: String): Result<String>
    suspend fun generateContent(prompt: String): Result<String>
}

class AiRepositoryImpl(private val geminiService: GeminiService) : AiRepository {
    override suspend fun summarize(text: String): Result<String> {
        val prompt = """
            Rangkum teks ini secara singkat padat dan jelas (maksimal 3 kalimat).
            Jangan pakai basa-basi, langsung berikan poin utamanya saja.
            Teks:
            "$text"
        """.trimIndent()
        return geminiService.generateContent(prompt)
    }

    override suspend fun translate(text: String, targetLang: String): Result<String> {
        val prompt = """
            Terjemahkan teks ini ke bahasa $targetLang.
            Hanya berikan hasil terjemahannya saja, dilarang menambahkan teks lain.
            Teks:
            "$text"
        """.trimIndent()
        return geminiService.generateContent(prompt)
    }

    override suspend fun generateContent(prompt: String): Result<String> {
        return geminiService.generateContent(prompt)
    }
}