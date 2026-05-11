package com.example.miprimeraappmultiplataforma

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Instant


fun currentTimeAt(location: String): String? {
    return try {
        val timezone = TimeZone.of(location)
        val now = kotlin.time.Clock.System.now()
        val time: LocalDateTime = now.toLocalDateTime(timezone) // 👈 tipo explícito
        val hour = time.hour.toString().padStart(2, '0')
        val minute = time.minute.toString().padStart(2, '0')
        "$hour:$minute"
    } catch (e: Exception) {
        null
    }
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        var timeAtLocation by remember { mutableStateOf("No location selected") }
        var location by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
        ) {
            TextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Enter location (e.g. Europe/Paris)") }
            )
            Button(onClick = {
                val time = currentTimeAt(location)
                timeAtLocation = if (time != null) {
                    "Time at $location: $time"
                } else {
                    "Invalid location: $location"
                }
            }) {
                Text("Show Time At Location")
            }
            Text(timeAtLocation)
        }
    }
}