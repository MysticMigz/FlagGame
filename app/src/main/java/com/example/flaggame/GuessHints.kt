package com.example.flaggame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class GuessHints : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessHintsGame()
        }
    }
}

fun navigateToMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

//RememberSaveable makes it so when I rotate the device the state remains the same. The game does not change when rotated.

@Composable
fun GuessHintsGame() {
    var randomCountryCode by rememberSaveable { mutableStateOf<String?>(null) }
    var countryName by rememberSaveable { mutableStateOf<String?>(null) }
    var displayedName by rememberSaveable { mutableStateOf<String?>(null) }
    var incorrectGuesses by rememberSaveable { mutableIntStateOf(0) }
    var gameEnded by rememberSaveable { mutableStateOf(false) }
    var guessedCharacters by rememberSaveable { mutableStateOf<List<Char>?>(null) }
    var textFieldValue by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    if (randomCountryCode == null) {
        randomCountryCode = getRandomCountryCode()
        countryName = countries[randomCountryCode ?: ""] ?: ""
        displayedName = countryName?.map { if (it == '-') it else '-' }?.joinToString(separator = "") ?: ""
        incorrectGuesses = 0
        gameEnded = false
        guessedCharacters = emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFFE6E6FA)), // Light purple background color
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display the flag image
        val flagResourceId = getImageResource(randomCountryCode ?: "")
        flagResourceId?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }

        Text(
            text = displayedName ?: "",
            style = TextStyle(fontSize = 24.sp),
            color = if (gameEnded && incorrectGuesses >= 3) Color.Blue else Color.Black, // Change color based on game state
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "Incorrect Guesses: $incorrectGuesses/3",
            style = TextStyle(fontSize = 18.sp),
            color = Color.Red,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        TextField(
            value = textFieldValue,
            onValueChange = {
                // Limit the users input to a single character
                if (it.length <= 1) {
                    textFieldValue = it
                }
            },
            label = { Text("Enter a character") },
            singleLine = true,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Button(
            onClick = {
                if (!gameEnded) {
                    val inputChar = textFieldValue.trim()
                    if (inputChar.isNotEmpty()) {
                        val indices = countryName?.indices?.filter {
                            countryName?.get(it)?.equals(
                                inputChar[0],
                                ignoreCase = true
                            ) ?: false
                        }
                        if (!indices.isNullOrEmpty()) {
                            displayedName = displayedName?.mapIndexed { index, c ->         //This represents the current character being processed in the displayedName. The mapIndexed iterates over it.
                                if (indices.contains(index)) countryName?.get(index) ?: c else c
                            }?.joinToString("")
                            if (displayedName.equals(countryName, ignoreCase = true)) {
                                gameEnded = true
                            }
                        } else {
                            incorrectGuesses++
                            if (incorrectGuesses >= 3) {
                                gameEnded = true
                            }
                        }
                        guessedCharacters = (guessedCharacters ?: emptyList()) + inputChar[0]
                    }
                    textFieldValue = ""
                } else {
                    randomCountryCode = getRandomCountryCode()
                    countryName = countries[randomCountryCode ?: ""] ?: ""
                    displayedName = countryName?.map { if (it == '-') it else '-' }?.joinToString(separator = "") ?: ""
                    incorrectGuesses = 0
                    gameEnded = false
                    guessedCharacters = emptyList()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(if (gameEnded) "Next" else "Submit") // Changes the button label based on game state
        }

// Display the list of guessed characters underneath
        guessedCharacters?.let { characters ->
            val filteredCharacters = characters.filter { it != '-' } // Filter out dashes
            if (filteredCharacters.isNotEmpty()) {
                Text(
                    text = "Guessed Characters: ${filteredCharacters.joinToString(separator = ", ")}",
                    style = TextStyle(fontSize = 18.sp),
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        // Display game result message
        if (gameEnded) {
            val message = if (displayedName.equals(countryName, ignoreCase = true)) {
                "CORRECT!"
            } else {
                val correctCountry = buildAnnotatedString {
                    pushStyle(SpanStyle(color = Color.Blue)) // Apply blue color to the text
                    append(countryName ?: "")
                    pop()
                }
                "WRONG! Correct country is $correctCountry"
            }
            val color = if (displayedName.equals(countryName, ignoreCase = true)) {
                Color.Green
            } else {
                Color.Red
            }
            Text(
                text = message,
                color = color,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }

    // Add TextButton to navigate back to main activity
    TextButton(
        onClick = { navigateToMainActivity(context) },
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Main Menu")
    }
}




