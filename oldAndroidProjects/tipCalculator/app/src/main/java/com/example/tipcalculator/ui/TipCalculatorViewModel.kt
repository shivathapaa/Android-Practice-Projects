package com.example.tipcalculator.ui

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tipcalculator.data.TipCalculatorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class TipCalculatorViewModel : ViewModel() {
    private val _tipUiState = MutableStateFlow(TipCalculatorUiState())
    val tipUiState: StateFlow<TipCalculatorUiState> = _tipUiState.asStateFlow()

    var amountInput by mutableStateOf("")
        private set

    fun updateUserAmountInput(userInput: String) {
        amountInput = userInput
    }

    var tipInput by mutableStateOf("")
        private set

    var roundUp by mutableStateOf(false)
        private set

    fun roundUpTotalAmount(userInput: Boolean) {
        roundUp = userInput
    }

    fun updateUserPercentTipInput(userInput: String) {
        tipInput = userInput
    }

    private val amount = amountInput.toDoubleOrNull() ?: 0.0
    private val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent, roundUp)



    /**
     * Calculates the tip based on the user input and format the tip amount
     * according to the local currency.
     * Example would be "$10.00".
     */
    fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
        var tip = tipPercent / 100 * amount
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        _tipUiState.update { currentState ->
            currentState.copy(tip = tip,
                amountInput = amount,
                tipInput = tipPercent)
        }
        return NumberFormat.getCurrencyInstance().format(tip)
    }
}