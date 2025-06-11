package com.example.flaggame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Miguel Silva
//w1945895 Uni of Westminster

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheFlagTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

@Composable
fun GuessTheFlagTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}

@Composable
fun GameScreen() {
    var hardMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFE6E6FA)), // Light purple background color
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Do you know your Flags?",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(bottom = 48.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.homescreenimage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        val context = LocalContext.current
        Button(
            onClick = {
                val intent = Intent(context, GuessTheFlag::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Guess the Flag")
        }

        Button(
            onClick = {
                val intent = Intent(context, GuessTheCountry::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Guess the Country")
        }

        Button(
            onClick = {
                val intent = Intent(context, GuessHints::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Guess-Hints")
        }

        // Advanced Level button
        Button(
            onClick = {
                val intent = Intent(context, AdvancedLevel::class.java)
                intent.putExtra("isHardModeEnabled", hardMode)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Advanced Level")
        }

        // Hard Mode switch
        HardModeSwitch(hardMode) { checked ->
            hardMode = checked
        }
    }
}

//Attempted the Hard Mode but was unsuccessful
@Composable
fun HardModeSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Text("Hard Mode")
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .padding(start = 8.dp),
        )
    }
}