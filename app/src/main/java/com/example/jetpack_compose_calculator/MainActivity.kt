package com.example.jetpack_compose_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_calculator.ui.theme.JetpackcomposecalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcomposecalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Preview
@Composable
fun Calculator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumberTextField();
        Spacer(modifier = Modifier.height(30.dp))
        NumberTextField();
    }
}

@Composable
fun NumberTextField() {
    val pattern = remember { Regex("^\\d+\$") }
    var firstNumber by remember { mutableStateOf("") }

    OutlinedTextField(
        value = firstNumber,
        onValueChange = {
            if (it.isEmpty() || it.matches(pattern)) {
                firstNumber = it
            }
        },
        label = { Text(text = "Add number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

//@Preview
@Composable
fun PreviewTextField() {
    NumberTextField()
}