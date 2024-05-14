package com.example.tipcalculator.data

data class TipCalculatorUiState(
    val tipInput: Double = 0.00,
    val roundUp: Boolean = false,
    val amountInput: Double = 0.00,
    val tip: Double = 0.00
)
