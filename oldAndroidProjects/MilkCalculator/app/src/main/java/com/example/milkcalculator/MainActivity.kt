package com.example.milkcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.milkcalculator.ui.theme.MilkCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MilkCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MilkRecordScreen()
                }
            }
        }
    }
}


@Composable
fun MilkRecordScreen() {
    var milkRecords by remember { mutableStateOf(emptyList<MilkRecord>()) }
    var quantity by remember { mutableStateOf("") }
    var payment by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MilkRecordList(milkRecords)

        Spacer(modifier = Modifier.height(16.dp))

        MilkRecordInput(
            onAddRecord = {
                if (quantity.isNotEmpty() && payment.isNotEmpty()) {
                    val newRecord = MilkRecord(quantity.toDouble(), payment.toDouble())
                    milkRecords = milkRecords + newRecord
                    quantity = ""
                    payment = ""
                }
            },
            quantity = quantity,
            onQuantityChange = { quantity = it },
            payment = payment,
            onPaymentChange = { payment = it }
        )
    }
}

@Composable
fun MilkRecordList(records: List<MilkRecord>) {
    Column {
        records.forEach { record ->
            Text(
                text = "Quantity: ${record.quantity} liters, Payment: $${record.payment}",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MilkRecordInput(
    onAddRecord: () -> Unit,
    quantity: String,
    onQuantityChange: (String) -> Unit,
    payment: String,
    onPaymentChange: (String) -> Unit
) {
    var focusRequester by remember { mutableStateOf(FocusRequester()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = quantity,
            onValueChange = { onQuantityChange(it) },
            label = { Text("Quantity (liters)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            ),
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = payment,
            onValueChange = { onPaymentChange(it) },
            label = { Text("Payment ($)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onAddRecord()
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier.weight(1f),
//            focusRequester = focusRequester
        )
    }
}

data class MilkRecord(val quantity: Double, val payment: Double)