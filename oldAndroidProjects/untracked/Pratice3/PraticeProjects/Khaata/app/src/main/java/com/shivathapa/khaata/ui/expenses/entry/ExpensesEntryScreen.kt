package com.shivathapa.khaata.ui.expenses.entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shivathapa.khaata.R
import com.shivathapa.khaata.ui.AppViewModelProvider
import com.shivathapa.khaata.ui.KhaataTopAppBar
import com.shivathapa.khaata.ui.common.BottomNextCancelButtons
import com.shivathapa.khaata.ui.common.EditableOutlineTextField
import com.shivathapa.khaata.ui.expenses.ExpenseDetails
import com.shivathapa.khaata.ui.expenses.ExpensesUiState
import com.shivathapa.khaata.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch


object ExpenseEntryDestination : NavigationDestination {
    override val route: String = "expense_entry"
    override val titleRes: Int = R.string.add_expense
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseEntryScreen(
    onNavigateUp: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExpensesEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            KhaataTopAppBar(
                title = stringResource(id = ExpenseEntryDestination.titleRes),
                navigateUp = onNavigateUp,
                canNavigateBack = true
            )
        }
    ) { innerPadding ->
        ExpensesEntryBody(
            expensesUiState = viewModel.expensesUiState,
            onExpenseValueChange = viewModel::updateExpensesUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveExpense()
                    navigateBack()
                }
            },
            onNavigateUp = onNavigateUp,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ExpensesEntryBody(
    expensesUiState: ExpensesUiState,
    onExpenseValueChange: (ExpenseDetails) -> Unit,
    onNavigateUp: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        ExpensesEntryField(
            expensesUiState = expensesUiState,
            expenseDetails = expensesUiState.expenseDetails,
            onExpenseValueChange = onExpenseValueChange,
            categoryName = "Category",
        )
        Spacer(modifier = Modifier.weight(1f))
        BottomNextCancelButtons(
            onSaveButtonClicked = onSaveClick,
            onCancelButtonClicked = onNavigateUp,
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_medium)
            )
        )
    }
}

@Composable
fun ExpensesEntryField(
    expenseDetails: ExpenseDetails,
    expensesUiState: ExpensesUiState,
    onExpenseValueChange: (ExpenseDetails) -> Unit,
    categoryName: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.padding_medium),
                horizontal = dimensionResource(id = R.dimen.padding_medium)
            )
    ) {
        CategoryIdentifier(
            categoryName = categoryName,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        EditableOutlineTextField(
            label = R.string.item,
            leadingIcon = { LeadingIconForEntry(icon = Icons.Outlined.ShoppingCart) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = expensesUiState.expenseDetails.item,
            onValueChanged = { onExpenseValueChange(expenseDetails.copy(item = it)) },
            modifier = Modifier
                .fillMaxWidth()
        )
        EditableOutlineTextField(
            label = R.string.date,
            leadingIcon = {
                LeadingIconForEntry(
                    icon = Icons.Outlined.DateRange
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = expensesUiState.expenseDetails.date ?: "",
            onValueChanged = { onExpenseValueChange(expenseDetails.copy(date = it)) },
            modifier = Modifier
                .fillMaxWidth()
        )
        QuantityAndVolumeOutlineTextField(
            expensesUiState = expensesUiState,
            expenseDetails = expenseDetails,
            onExpenseValueChange = onExpenseValueChange,
            modifier = Modifier
        )
        DoubleCurrencyAndAmountOutlineTextEntry(
            expensesUiState = expensesUiState,
            expenseDetails = expenseDetails,
            onExpenseValueChange = onExpenseValueChange,
            modifier = Modifier
        )
    }
}

@Composable
fun CategoryIdentifier(
    categoryName: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = categoryName,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_extra_small))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        Image(
            painter = painterResource(id = R.drawable.small_farmer_milking_cow),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.expense_entry_image_size))
                .padding(bottom = dimensionResource(id = R.dimen.padding_extra_small))
                .clip(MaterialTheme.shapes.small)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDatePicker(
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(true) }
    val datePickerState = rememberDatePickerState()

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    // update textFieldValue
                }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
//                dateFormatter = DatePickerFormatter()
            )
        }
    }
}

@Composable
fun LeadingIconForEntry(
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Icon(imageVector = icon, contentDescription = null)
}

@Composable
fun QuantityAndVolumeOutlineTextField(
    expenseDetails: ExpenseDetails,
    expensesUiState: ExpensesUiState,
    onExpenseValueChange: (ExpenseDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        EditableOutlineTextField(
            label = R.string.quantity_entry,
            leadingIcon = null,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = expensesUiState.expenseDetails.quantity ?: "",
            onValueChanged = { onExpenseValueChange(expenseDetails.copy(quantity = it)) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_tween_small_medium)))
        EditableOutlineTextField(
            label = R.string.volume,
            leadingIcon = null,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = expensesUiState.expenseDetails.volume.toString(),
            onValueChanged = { onExpenseValueChange(expenseDetails.copy(volume = it)) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_tween_small_medium)))
        EditableOutlineTextField(
            label = R.string.unit,
            leadingIcon = null,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = expensesUiState.expenseDetails.unit.toString(),
            onValueChanged = { onExpenseValueChange(expenseDetails.copy(unit = it)) },
            modifier = Modifier.weight(0.6f)
        )
    }
}

@Composable
fun DoubleCurrencyAndAmountOutlineTextEntry(
    expenseDetails: ExpenseDetails,
    expensesUiState: ExpensesUiState,
    onExpenseValueChange: (ExpenseDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        EditableOutlineTextField(
            label = R.string.currency,
            leadingIcon = null,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            value = expensesUiState.expenseDetails.currency ?: "",
            onValueChanged = { onExpenseValueChange(expenseDetails.copy(currency = it)) },
            modifier = Modifier.weight(0.5f)
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_tween_small_medium)))
        EditableOutlineTextField(
            label = R.string.amount_per_unit,
            leadingIcon = null,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = expensesUiState.expenseDetails.amount,
            onValueChanged = { onExpenseValueChange(expenseDetails.copy(amount = it)) },
            modifier = Modifier.weight(1f)
        )
    }
}