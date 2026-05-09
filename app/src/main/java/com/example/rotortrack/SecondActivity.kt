package com.example.rotortrack

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

class SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pilotName = intent.getStringExtra("pilot_name") ?: "Student Pilot"

        setContent {
            RotorTrackTheme {
                SecondaryScreen(
                    pilotName = pilotName,
                    onBackClick = { finish() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryScreen(
    pilotName: String,
    onBackClick: () -> Unit
) {
    var flightDate by remember { mutableStateOf("") }
    var flightDuration by remember { mutableStateOf("") }
    var flightType by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var logSummary by remember { mutableStateOf("") }
    var totalFlightHours by remember { mutableStateOf(0.0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Flight Log") }
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
                text = "Welcome, $pilotName",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = flightDate,
                onValueChange = { flightDate = it },
                label = { Text("Flight Date") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = flightDuration,
                onValueChange = { flightDuration = it },
                label = { Text("Flight Duration") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = flightType,
                onValueChange = { flightType = it },
                label = { Text("Flight Type") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val enteredHours = flightDuration.toDoubleOrNull() ?: 0.0
                    totalFlightHours += enteredHours

                    logSummary =
                        "Flight logged: $flightDuration hours on $flightDate. Type: $flightType."
                }
            ) {
                Text("Save Flight Log")
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Total Flight Hours: $totalFlightHours",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(12.dp))

            if (logSummary.isNotEmpty()) {
                Text(logSummary)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onBackClick) {
                Text("Back to Main")
            }
        }
    }
}