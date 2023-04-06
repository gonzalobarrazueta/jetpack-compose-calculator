package com.example.jetpack_compose_calculator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

data class MathOperator(val icon: ImageVector, val description: String, val onClick: () -> Unit)

var operatorType: String by mutableStateOf("")
var operatorClicked: Boolean by mutableStateOf(false)

// this variable will be used to store the numbers that will be used to perform any math operation
var numbers = mutableListOf<Int>()

val operatorsFunctionality = listOf(
    MathOperator(Icons.Outlined.Add, "Plus", {
        operatorClicked = true
        operatorType = "plus"
    }),
    MathOperator(Icons.Default.Remove, "Minus", {
        operatorClicked = true
        operatorType = "minus"
    })
)

var calculateButtonClicked by mutableStateOf(false)
