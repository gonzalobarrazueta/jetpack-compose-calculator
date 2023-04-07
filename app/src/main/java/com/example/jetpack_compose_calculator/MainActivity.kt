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
import kotlin.math.pow

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

    var number = remember { mutableStateOf("") }
    var calculatedValue = remember { mutableStateOf(0.0f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppBar()
        Text(
            text = "Calculate a number",
            fontSize = 15.sp
        )
        NumberTextField(number)
        Spacer(modifier = Modifier.height(30.dp))
        ListOfOperators()
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "The operator selected is $operatorType")
        Spacer(modifier = Modifier.height(30.dp))

        if (operatorClicked && number.value.isNotEmpty()) {
            operations.add(OperationElement(number.value.toFloat(), operatorType))

            operatorClicked = false
            number.value = ""
        }

        // for development purposes, print the operations array
        Text(text = "Numbers in the array")
        if (operations.isNotEmpty()) {
            for (operation in operations) {
                if (operation.operator != null) {
                    Text(text = "${operation.number} ${operation.operator} ")
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        CalculateButton(number, calculatedValue)
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "The result is ${String.format("%.2f", calculatedValue.value)}",
            fontSize = 20.sp
        )
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
fun NumberTextField(number: MutableState<String> ) {
    val pattern = remember { Regex("^[0-9.]+\$") }

    OutlinedTextField(
        value = number.value,
        onValueChange = {
            if (it.isEmpty() || it.matches(pattern)) {
                number.value = it
            }
        },
        label = { Text(text = "Add number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun ListOfOperators() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
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
fun CalculateButton(number: MutableState<String>, calculatedValue: MutableState<Float>) {
    Button(
        onClick = {
            // there's 1 last value that needs to be added to the array
            if (number.value.isNotEmpty()) {
                operations.add(OperationElement(number.value.toFloat(), null))
            }

            if (operations.isNotEmpty()) {
                calculatedValue.value = performOperation()
                number.value = ""
            }
        }
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

fun performOperation(): Float {
    var result = operations[0].number
    var op = operations[0].operator
    var num: Float

    for (i in 1 until operations.size) {

        num = operations[i].number

        when (op) {
            "plus" -> result += num
            "minus" -> result -= num
            "multiplication" -> result *= num
            "division" -> result /= num
            "exponent" -> result = result.pow(num)
            "module" -> result %= num
        }
        if (op != null) {
            op = operations[i].operator
        }
    }
    operations.clear()
    return result
}

@Preview
@Composable
fun PreviewComponent() {
    MathOperator(operator = MathOperator(Icons.Outlined.Add, "Plus", {}))
}