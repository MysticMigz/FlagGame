package com.example.flaggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class GuessTheCountry : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheCountryContent()
        }
    }
}

@Composable
fun GuessTheCountryContent() {
    var currentRound by rememberSaveable { mutableIntStateOf(0) }
    var totalCorrectAnswers by rememberSaveable { mutableIntStateOf(0) }
    val maxRounds = 5 // Total number of rounds
    var randomCountryCode by rememberSaveable { mutableStateOf("") }
    var countryName by rememberSaveable { mutableStateOf("") }
    var previousCountryName by rememberSaveable { mutableStateOf<String?>(null) }
    var previousFeedbackMessage by rememberSaveable { mutableStateOf<String?>(null) }
    var gameStarted by rememberSaveable { mutableStateOf(false) }
    var hasSubmitted by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6E6FA)), // Light purple background color
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main Menu button in the top left corner
        TextButton(
            onClick = { navigateToMainActivity(context) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Main Menu")
        }

        // Display current round number
        Text(
            text = "Round: ${currentRound + 1} / $maxRounds",
            modifier = Modifier.padding(top = 16.dp)
        )

        if (currentRound < maxRounds) {
            if (!gameStarted) {
                gameStarted = true
                randomCountryCode = getRandomCountryCode()
                countryName = countries[randomCountryCode] ?: "Unknown"
            }
            DisplayRandomFlagAndName(
                randomCountryCode = randomCountryCode,
                countryName = countryName,
                previousCountryName = previousCountryName,
                previousFeedbackMessage = previousFeedbackMessage,
                onAnswerSubmitted = { answerCorrect ->
                    if (answerCorrect) {
                        totalCorrectAnswers++
                    }
                    currentRound++
                    previousCountryName = countryName
                    previousFeedbackMessage = if (answerCorrect) "CORRECT!" else "WRONG!"
                    randomCountryCode = getRandomCountryCode()
                    countryName = countries[randomCountryCode] ?: "Unknown"
                    gameStarted = false
                    hasSubmitted = true
                },
                hasSubmitted = hasSubmitted
            )
        } else {
            // Game Over
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = if (previousFeedbackMessage == "CORRECT!") "CORRECT!" else "WRONG!",
                    color = if (previousFeedbackMessage == "CORRECT!") Color.Green else Color.Red,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Total Correct Answers: $totalCorrectAnswers / $maxRounds",
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                previousCountryName?.let { name ->
                    Text(
                        text = "Correct country: $name",
                        color = Color.Blue,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                // Button to play the game again
                Button(
                    onClick = {
                        // Reset game state
                        currentRound = 0
                        totalCorrectAnswers = 0
                        gameStarted = false
                        previousCountryName = null
                        previousFeedbackMessage = null
                        hasSubmitted = false
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Play Again")
                }
            }
        }
    }
}

@Composable
fun DisplayRandomFlagAndName(
    randomCountryCode: String,
    countryName: String,
    previousCountryName: String?,
    previousFeedbackMessage: String?,
    onAnswerSubmitted: (Boolean) -> Unit,
    hasSubmitted: Boolean
) {
    var userSelection by remember { mutableStateOf("") }
    var answerSubmitted by remember { mutableStateOf(false) }
    var answerCorrect by remember { mutableStateOf(false) }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }
    var hasFeedbackDisplayed by remember { mutableStateOf(false) }

    val flagDrawableId = getImageResource(randomCountryCode)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display flag image
        flagDrawableId?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = "Flag",
                modifier = Modifier.size(200.dp)
            )
        }

        // Display feedback message
        if (answerSubmitted && !answerCorrect && feedbackMessage != null && !hasFeedbackDisplayed) {
            val color = Color.Red
            Text(
                text = feedbackMessage!!,
                color = color,
                modifier = Modifier.padding(top = 16.dp)
            )
            hasFeedbackDisplayed = true
        }

        // Display previous feedback message and country name
        previousFeedbackMessage?.let { message ->
            val color = if (message == "CORRECT!") Color.Green else Color.Red
            Text(
                text = message,
                color = color,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        previousCountryName?.let { name ->
            Text(
                text = "Correct country: $name",
                color = Color.Blue,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Submit button to check the user's answer
        Button(
            onClick = {
                // Check if the user has made a selection before submitting
                if (userSelection.isNotEmpty()) {
                    // Check if the user's selection matches the correct country name
                    answerCorrect = (userSelection == countryName)
                    answerSubmitted = true // Set answerSubmitted to true

                    // Set feedback message
                    feedbackMessage = if (answerCorrect) "CORRECT!" else "WRONG!"
                    hasFeedbackDisplayed = false

                    // Notify the parent composable about the answer correctness
                    onAnswerSubmitted(answerCorrect)

                    // Reset user selection
                    userSelection = ""
                }
            },
            enabled = userSelection.isNotEmpty(),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(if (hasSubmitted) "Next" else "Submit")
        }

        // Displays the list of country names for the user to choose from in the format of a lazy column
        LazyColumn(
            modifier = Modifier
                .padding(top = 16.dp)
                .background(Color.LightGray)
                .border(width = 1.dp, color = Color.Black)
                .padding(4.dp) // Adding padding to the border
                .padding(bottom = 16.dp)
                .height(300.dp)

        ) {
            items(countries.values.toList()) { country ->
                val isSelected = (userSelection == country)
                Text(
                    text = country,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            // Update the user's selection
                            userSelection = country
                        }
                        .background(if (isSelected) Color.Gray else Color.Transparent)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDisplayRandomFlagAndName() {
    DisplayRandomFlagAndName(
        randomCountryCode = "US", // Provide a sample country code
        countryName = "United States", // Provide a sample country name
        previousCountryName = "Previous Country",
        previousFeedbackMessage = "CORRECT!",
        onAnswerSubmitted = {},
        hasSubmitted = true
    )
}




