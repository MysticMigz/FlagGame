package com.example.flaggame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random



class GuessTheFlag : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheFlagContent()
        }
    }

    @Composable
    fun GuessTheFlagContent() {
        var roundCount by rememberSaveable { mutableIntStateOf(1) }
        var score by rememberSaveable { mutableIntStateOf(0) }
        var shuffledCountryCodes by rememberSaveable {
            mutableStateOf(
                generateUniqueCountryCodes()
            )
        }
        var correctCountryIndex by rememberSaveable { mutableIntStateOf(Random.nextInt(3)) }
        var correctFlagClicked by rememberSaveable { mutableStateOf(false) }
        var incorrectFlagClicked by rememberSaveable { mutableStateOf(false) }
        var flagClicked by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(Color(0xFFE6E6FA)),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(
                onClick = {
                    navigateBackToMainMenu()
                },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    text = "Main Menu",
                    color = Color.Black
                )
            }

            Text(text = "Round: $roundCount / 10")

            val randomCountryCode = shuffledCountryCodes[correctCountryIndex]
            val countryName = countries[randomCountryCode] ?: "Unknown"
            if (countryName == "Unknown") {
                Log.d("GuessTheFlag", "Unknown country code: $randomCountryCode") // I was using this to try find a flag that was returning as unknown
            }
            Text(
                text = "Which country is this? ($countryName)",
                fontSize = 16.sp
            )

            for (i in 0 until 3) {
                val countryCode = shuffledCountryCodes[i]
                val isCorrect = i == correctCountryIndex

                val imageResource = getImageResource(countryCode)
                imageResource?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .clickable(enabled = !flagClicked) {
                                if (!flagClicked) {
                                    flagClicked = true
                                    if (isCorrect) {
                                        correctFlagClicked = true
                                        score++
                                    } else {
                                        incorrectFlagClicked = true
                                    }
                                    if (roundCount < 10) {
                                        roundCount++
                                    }
                                }
                            }
                    )
                }
            }

            if (correctFlagClicked) {
                Text(
                    text = "CORRECT!",
                    color = Color.Green,
                    fontWeight = FontWeight.Bold
                )
            }

            if (incorrectFlagClicked) {
                Text(
                    text = "INCORRECT!",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }

            if (roundCount >= 10) {
                GameOverMessage(score = score) {
                    score = 0
                    roundCount = 1
                    correctFlagClicked = false
                    incorrectFlagClicked = false
                    flagClicked = false
                    shuffledCountryCodes = generateUniqueCountryCodes()
                    correctCountryIndex = Random.nextInt(3)
                }
            } else {
                Button(
                    onClick = {
                        if (flagClicked) {
                            correctFlagClicked = false
                            incorrectFlagClicked = false
                            flagClicked = false
                            shuffledCountryCodes = generateUniqueCountryCodes()
                            correctCountryIndex = Random.nextInt(3)
                        }
                    },
                    enabled = flagClicked // Enable the button only when a flag is clicked
                ) {
                    Text("Next")
                }
            }
        }
    }

    // Function to generate a list of three unique country codes
    private fun generateUniqueCountryCodes(): List<String> {
        val uniqueCountryCodes = mutableListOf<String>()
        while (uniqueCountryCodes.size < 3) {
            val randomCountryCode = getRandomCountryCode()
            if (!uniqueCountryCodes.contains(randomCountryCode)) {
                uniqueCountryCodes.add(randomCountryCode)
            }
        }
        return uniqueCountryCodes
    }

    // Function to navigate back to the main menu
    private fun navigateBackToMainMenu() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun GameOverMessage(score: Int, onPlayAgainClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Game Over. Your final score is $score out of 10.",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(onClick = onPlayAgainClick) {
            Text("Play Again")
        }
    }
}

@Preview
@Composable
fun PreviewGuessTheFlagContent() {

}