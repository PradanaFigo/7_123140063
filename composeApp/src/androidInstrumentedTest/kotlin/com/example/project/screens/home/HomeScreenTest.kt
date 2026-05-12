package com.example.project.screens.home

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.project.MainActivity
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun emptyState_isDisplayed_whenNoNotes() {
        rule.onNodeWithTag("search_input").performTextInput("xyz123randomteks")

        // Sekarang tag ini pasti muncul karena hasil pencarian kosong
        rule.onNodeWithTag("empty_state_text").assertIsDisplayed()
    }

    @Test
    fun searchBar_allowsInput() {
        // Mengetik "Kuliah" di kolom pencarian
        rule.onNodeWithTag("search_input").performTextInput("Kuliah")

        // Memastikan teks yang diketik tampil di layar
        rule.onNodeWithText("Kuliah").assertExists()
    }

    @Test
    fun settingsButton_isClickable() {
        // Mencari tombol Settings berdasarkan deskripsi kontennya
        rule.onNodeWithContentDescription("Settings").performClick()
    }
}