package com.example.rotortrack

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rotortrack.ui.theme.RotorTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RotorTrackTheme {
                MainScreen(
                    onOpenFlightLog = {
                        val intent = Intent(this, SecondaryActivity::class.java)
                        intent.putExtra("pilot_name", "Student Pilot")
                        startActivity(intent)
                    },
                    onOpenPreferences = {
                        val intent = Intent(this, PreferencesActivity::class.java)
                        startActivity(intent)
                    },
                    onOpenHelp = {
                        val intent = Intent(this, HelpActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onOpenFlightLog: () -> Unit,
    onOpenPreferences: () -> Unit,
    onOpenHelp: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("RotorTrack") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Helicopter Training Records",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Track your training progress and log flights.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onOpenFlightLog) {
                Text("Open Flight Log")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = onOpenPreferences) {
                Text("Open Preferences")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = onOpenHelp) {
                Text("Open Help")
            }
        }
    }
}