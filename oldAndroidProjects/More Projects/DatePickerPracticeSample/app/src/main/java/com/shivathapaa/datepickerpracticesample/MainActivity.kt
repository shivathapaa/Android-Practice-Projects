package com.shivathapaa.datepickerpracticesample

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivathapaa.datepickerpracticesample.ui.theme.DatePickerPracticeSampleTheme
import java.time.LocalDate
import java.time.ZoneOffset
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatePickerPracticeSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        DatePickerSample(modifier = Modifier)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerSample(
    modifier: Modifier = Modifier
) {
    var showModalDatePicker by remember { mutableStateOf(false) }


    val handelDismissEventOfShowModalDatePicker: (Boolean) -> Unit =
        { showModalDatePicker = false }

    Button(modifier = modifier, onClick = { showModalDatePicker = true }
    ) {
        Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
        Text(modifier = Modifier.padding(8.dp), text = "Modal Date Picker")
    }
    if (showModalDatePicker) {
        MyModalDatePicker(showModalDatePicker = handelDismissEventOfShowModalDatePicker)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
object PastOrPresentSelectableDates: SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis <= System.currentTimeMillis()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun isSelectableYear(year: Int): Boolean {
        return year <= LocalDate.now().year
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyModalDatePicker(
    showModalDatePicker: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {


    val initialSelectedDate = LocalDate.of(2023, 7, 13)
        .atStartOfDay(ZoneOffset.UTC)
        .toInstant()
        .toEpochMilli()

    val initialDisplayedMonth = LocalDate.of(2026, 7, 23)
        .atStartOfDay(ZoneOffset.UTC)
        .toInstant()
        .toEpochMilli()

    val todayMillis = LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()

    val datePickerState = rememberDatePickerState(
        selectableDates = PastOrPresentSelectableDates,
        initialSelectedDateMillis = initialSelectedDate,
        initialDisplayedMonthMillis = initialDisplayedMonth,
        yearRange = 2000..2100)
    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }

    DatePickerDialog(
        onDismissRequest = { showModalDatePicker(false) },
        confirmButton = {
            TextButton(
                onClick = {
                    showModalDatePicker(false)
                },
                enabled = confirmEnabled.value
            ) {
                Text(text = "Done")
            }
        },
        dismissButton = {
            TextButton(onClick = { showModalDatePicker(false) }) {
                Text(text = "Cancel")
            }
        },
        modifier = modifier
    ) {
        DatePicker(state = datePickerState)
    }
}