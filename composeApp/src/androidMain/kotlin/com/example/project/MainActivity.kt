package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.project.data.DatabaseDriverFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Kirim 'this' (Context) ke factory Android
        val factory = DatabaseDriverFactory(this)
        setContent {
            App(driverFactory = factory)
        }
    }
}