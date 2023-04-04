package com.example.jetpack_compose_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun Calculator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppBar()
        NumberTextField()
        Spacer(modifier = Modifier.height(30.dp))
        ListOfOperators()
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "The operator selected is $operatorType")
        Spacer(modifier = Modifier.height(30.dp))
        CalculateButton()
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        title = { Text(text = "Calculator")},
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    )
}

@Composable
fun NumberTextField() {
    val pattern = remember { Regex("^\\d+\$") }
    var number by remember { mutableStateOf("") }

    OutlinedTextField(
        value = number,
        onValueChange = {
            if (it.isEmpty() || it.matches(pattern)) {
                number = it
            }
        },
        label = { Text(text = "Add number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun ListOfOperators() {
    LazyRow {
        items(operatorsFunctionality) { operator ->
            MathOperator(operator)
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun MathOperator(operator: MathOperator) {
    IconButton(
        onClick = { operator.onClick() },
        modifier = Modifier
            .size(48.dp)
            .padding(all = 10.dp)
            .border(1.5.dp, MaterialTheme.colors.primary, CircleShape),
    ) {
        Icon(operator.icon, operator.description)
    }
}

@Composable
fun CalculateButton() {
    Button(
        onClick = { }
    ) {
        Text(
            text = "Calculate",
            style = MaterialTheme.typography.button.copy(fontSize = 20.sp)
        )

        IconButton(onClick = { }) {
            Icon(
                Icons.Outlined.Calculate,
                contentDescription = "Perform math operation",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewComponent() {
    MathOperator(operator = MathOperator(Icons.Outlined.Add, "Plus", {}))
}