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

val operatorsFunctionality = listOf(
    MathOperator(Icons.Outlined.Add, "Plus", { operatorType = "plus" }),
    MathOperator(Icons.Default.Remove, "Minus", { operatorType = "minus" })
)

