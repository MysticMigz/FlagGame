package com.example.flaggame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


class AdvancedLevel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedLevelScreen()
        }
    }
}

@Composable
fun AdvancedLevelScreen() {
    var flags by rememberSaveable { mutableStateOf(getRandomFlags(3)) }
    var attemptsLeft by rememberSaveable { mutableIntStateOf(3) }
    var isGameEnded by rememberSaveable { mutableStateOf(false) }
    var isPlayMode by rememberSaveable { mutableStateOf(true) }
    var submittedAnswers by rememberSaveable { mutableStateOf(List(flags.size) { "" }) }
    var answerSubmitted by rememberSaveable { mutableStateOf(List(flags.size) { false }) }
    var textFieldValues by rememberSaveable { mutableStateOf(List(flags.size) { "" }) }
    var isTextFieldEnabled by rememberSaveable { mutableStateOf(List(flags.size) { true }) }
    var answer1 by rememberSaveable { mutableStateOf("") }
    var answer2 by rememberSaveable { mutableStateOf("") }
    var answer3 by rememberSaveable { mutableStateOf("") }
    var finalScore by rememberSaveable { mutableIntStateOf(0) }
    var totalScore by rememberSaveable { mutableIntStateOf(0) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(20.dp)
            .background(Color(0xFFE6E6FA)),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = { navigateBackToMainActivity(context) },
        ) {
            Text("Main Menu")
        }

        Text(
            text = "Final Score: $finalScore",
        )

        flags.forEachIndexed { index, flag ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = flag.imageRes),
                    contentDescription = flag.countryName,
                    modifier = Modifier.size(80.dp)
                )

                Text("Guess the country name:", modifier = Modifier.padding(top = 2.dp))

                val textFieldValue = textFieldValues[index]

                TextField(
                    value = if (index == 0) answer1 else if (index == 1) answer2 else answer3,
                    onValueChange = { newValue ->
                        when (index) {
                            0 -> answer1 = newValue
                            1 -> answer2 = newValue
                            2 -> answer3 = newValue
                        }
                        textFieldValues = textFieldValues.toMutableList().also { it[index] = newValue }
                        submittedAnswers = submittedAnswers.toMutableList().also { it[index] = newValue }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp)
                        .background(
                            when {
                                !isTextFieldEnabled[index] -> Color.Gray
                                checkAnswer(textFieldValues[index], flags[index]) -> Color.Green
                                else -> Color.Transparent
                            }
                        ),
                    textStyle = TextStyle(color =
                    when {
                        checkAnswer(textFieldValues[index], flags[index]) && answerSubmitted[index] -> Color.Green // Text turns green if answer correct and submitted
                        checkAnswer(textFieldValues[index], flags[index]) -> Color.Black // Text stays black if answer correct but not submitted
                        textFieldValues[index].isNotBlank() -> Color.Black // Text stays black if answer is incorrect and submitted
                        else -> Color.Red // Text turns red if answer incorrect and submitted
                    }
                    ),
                    label = { Text("Answer ${index + 1}") },
                    enabled = isPlayMode && isTextFieldEnabled[index],
                    singleLine = true
                )

                if (!isPlayMode && attemptsLeft == 0) {
                    Text(
                        text = "Correct country: ${flag.countryName}",
                        color = Color.Blue,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                if (!isPlayMode) {
                    val isCorrect = textFieldValue.equals(flag.countryName, ignoreCase = true)
                    val message = if (isCorrect && !answerSubmitted[index]) {
                        answerSubmitted = answerSubmitted.toMutableList().also { it[index] = true }
                        "Correct! ${flag.countryName}"
                    } else if (!answerSubmitted[index]) {
                        "Incorrect!"
                    } else {
                        ""
                    }
                    val color = if (isCorrect) Color.Green else Color.Red
                    Text(
                        text = message,
                        style = TextStyle(color = color),
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    if (isCorrect) {
                        isTextFieldEnabled = isTextFieldEnabled.toMutableList().also { it[index] = false }
                    }
                }
            }
        }

        Text(
            text = "Attempts left: $attemptsLeft",
            color = if (attemptsLeft == 3) Color.Black else if (attemptsLeft == 2) Color.Yellow else Color.Red,
            modifier = Modifier.padding(vertical = 1.dp)
        )

        if (!isGameEnded) {
            Button(
                onClick = {
                    if (!isGameEnded) {
                        val correctAnswersCount = (flags.indices).count { index ->
                            submittedAnswers[index].equals(flags[index].countryName, ignoreCase = true)
                        }

                        if (correctAnswersCount == flags.size) {
                            isGameEnded = true
                            finalScore = 3
                        } else {
                            attemptsLeft--
                            if (attemptsLeft <= 0) {
                                isGameEnded = true
                                flags.forEachIndexed { index, flag ->
                                    if (submittedAnswers[index].equals(flag.countryName, ignoreCase = true)) {
                                        finalScore++
                                    }
                                }
                            }
                        }

                        totalScore += finalScore

                        flags.forEachIndexed { index, flag ->
                            if (checkAnswer(textFieldValues[index], flag)) {
                                answerSubmitted = answerSubmitted.toMutableList().apply { set(index, true) }
                                isTextFieldEnabled = isTextFieldEnabled.toMutableList().apply { set(index, false) }
                            }
                        }
                    }
                }
            ) {
                Text("Submit")
            }
        }

        if (isGameEnded) {
            isPlayMode = false
            Button(
                onClick = {
                    attemptsLeft = 3
                    isGameEnded = false
                    isPlayMode = true
                    answerSubmitted = List(flags.size) { false }
                    submittedAnswers = List(flags.size) { "" }
                    textFieldValues = List(flags.size) { "" }
                    flags = getRandomFlags(3)
                    isTextFieldEnabled = List(flags.size) { true }

                    //Reset answers to empty text-fields
                    answer1 = ""
                    answer2 = ""
                    answer3 = ""
                },
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text("Play again?")
            }
        }
    }
}

fun navigateBackToMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}

fun getRandomFlags(count: Int): List<Flag> {
    val uniqueCountryCodes = mutableSetOf<String>()

    while (uniqueCountryCodes.size < count) {
        val countryCode = getRandomCountryCode()
        uniqueCountryCodes.add(countryCode)
    }

    val flags = uniqueCountryCodes.map { countryCode ->
        val countryName = countries[countryCode] ?: "Unknown"
        val imageRes = getImageResource(countryCode) ?: R.drawable.placeholder
        Flag(countryName, imageRes)
    }

    for (flag in flags) {
        Log.d("FlagGame", "Country name: ${flag.countryName}")
    }

    return flags
}

data class Flag(val countryName: String, val imageRes: Int)

fun checkAnswer(answer: String, flag: Flag): Boolean {
    return answer.equals(flag.countryName, ignoreCase = true)
}