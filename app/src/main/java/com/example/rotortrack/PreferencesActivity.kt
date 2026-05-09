package com.example.rotortrack

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rotortrack.ui.theme.RotorTrackTheme

class PreferencesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("rotortrack_preferences", Context.MODE_PRIVATE)

        setContent {
            RotorTrackTheme {
                PreferencesScreen(
                    savedTargetHours = sharedPreferences.getString("target_hours", "40") ?: "40",
                    savedReminderStatus = sharedPreferences.getBoolean("reminders_enabled", false),
                    onSavePreferences = { targetHours, remindersEnabled ->
                        sharedPreferences.edit()
                            .putString("target_hours", targetHours)
                            .putBoolean("reminders_enabled", remindersEnabled)
                            .apply()
                    },
                    onBackClick = { finish() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesScreen(
    savedTargetHours: String,
    savedReminderStatus: Boolean,
    onSavePreferences: (String, Boolean) -> Unit,
    onBackClick: () -> Unit
) {
    var targetHours by remember { mutableStateOf(savedTargetHours) }
    var remindersEnabled by remember { mutableStateOf(savedReminderStatus) }
    var savedMessage by remember { mutableStateOf("") }
    var measurementUnit by remember { mutableStateOf("Hours") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Preferences") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "RotorTrack Settings",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = targetHours,
                onValueChange = { targetHours = it },
                label = { Text("Target Flight Hours") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = remindersEnabled,
                    onCheckedChange = { remindersEnabled = it }
                )
                Text("Enable flight reminders")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Measurement Unit",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = measurementUnit == "Hours",
                    onClick = { measurementUnit = "Hours" }
                )
                Text("Hours")

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = measurementUnit == "Minutes",
                    onClick = { measurementUnit = "Minutes" }
                )
                Text("Minutes")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onSavePreferences(targetHours, remindersEnabled)
                    savedMessage = "Preferences saved."
                }
            ) {
                Text("Save Preferences")
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (savedMessage.isNotEmpty()) {
                Text(savedMessage)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onBackClick) {
                Text("Back to Main")
            }
        }
    }
}