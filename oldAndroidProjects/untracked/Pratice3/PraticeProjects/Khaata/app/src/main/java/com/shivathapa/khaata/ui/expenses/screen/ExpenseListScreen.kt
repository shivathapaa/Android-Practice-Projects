package com.shivathapa.khaata.ui.expenses.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shivathapa.khaata.R
import com.shivathapa.khaata.data.Expense
import com.shivathapa.khaata.model.ExpenseCategory
import com.shivathapa.khaata.model.Expenses
import com.shivathapa.khaata.model.ExpensesDetail
import com.shivathapa.khaata.ui.AppViewModelProvider
import com.shivathapa.khaata.ui.KhaataFloatingActionButton
import com.shivathapa.khaata.ui.KhaataTopAppBar
import com.shivathapa.khaata.ui.common.SurfaceComplimentText
import com.shivathapa.khaata.ui.common.SurfaceText
import com.shivathapa.khaata.ui.common.fake.fakeListOfExpenseCategory
import com.shivathapa.khaata.ui.navigation.NavigationDestination
import com.shivathapa.khaata.ui.theme.KhaataTheme
import java.text.NumberFormat

object ExpensesDestination : NavigationDestination {
    override val route: String = "expenses"
    override val titleRes: Int = R.string.expenses
    const val categoryIdArg = "categoryId"
    val routeWithArgs = "$route/{$categoryIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesListScreen(
    navigateToExpenseEntry: () -> Unit,
    navigateToExpenseEdit: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: ExpenseListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val expenseListUiState by viewModel.expenseListUiState.collectAsState()

    Scaffold(
        topBar = {
            KhaataTopAppBar(
                title = stringResource(id = ExpensesDestination.titleRes),
                navigateUp = onNavigateUp,
                canNavigateBack = canNavigateBack
            )
        },
        floatingActionButton = {
            KhaataFloatingActionButton(
                extended = true,
                icon = Icons.Default.Add,
                text = stringResource(R.string.add),
                onClick = navigateToExpenseEntry
            )
        }
    ) { innerPadding ->
        ExpensesList(
            expensesListByCategory = expenseListUiState.expenseList,
            navigateToExpenseEdit = navigateToExpenseEdit,
            modifier = Modifier.padding(innerPadding)
        )
    }

}

@Composable
fun ExpensesList(
    expensesListByCategory: List<Expense>,
    navigateToExpenseEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(
            horizontal = dimensionResource(id = R.dimen.padding_small),
            vertical = dimensionResource(id = R.dimen.padding_small)
        )
    ) {
        items(
            items = expensesListByCategory,
            key = { expensesListByCategory -> expensesListByCategory.expenseId }) { expense ->
            ExpenseCard(
                expense = expense,
                navigateToExpenseEdit = navigateToExpenseEdit,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun ExpenseCard(
    expense: Expense,
    navigateToExpenseEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    var detailsExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .padding(
                bottom = dimensionResource(id = R.dimen.padding_extra_small)
            )
            .clickable(onClick = navigateToExpenseEdit)
            .animateContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            ExpenseDate(
                date = expense.date,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_small),
                        end = dimensionResource(id = R.dimen.padding_small)
                    )
            )
            ExpenseName(
                name = expense.item,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
//                    .align(Alignment.Top)
            )
            ExpenseAmount(
                amount = expense.amount,
                onClick = { detailsExpanded = !detailsExpanded }
            )
//            EditExpense(
//                onClick = { },
//                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_small))
//            )
        }
        if (detailsExpanded) {
            Divider(
                thickness = 0.2.dp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.alpha(0.5f)
            )
            ExpensesDetails(
                expense = expense,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ExpenseDate(
    date: String,
    modifier: Modifier = Modifier
) {
    val (month, day) = date.split(" ")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = month,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = day,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
fun ExpenseName(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
fun ExpenseAmount(
    amount: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            horizontalAlignment = Alignment.End
        ) {
            SurfaceComplimentText(
                text = stringResource(id = R.string.total_caps),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 8.sp,
                    lineHeight = 0.sp,
                    letterSpacing = 0.4.sp
                )
            )
            SurfaceComplimentText(
                text = amount.toString(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun EditExpense(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = stringResource(id = R.string.edit),
            modifier = Modifier.clip(MaterialTheme.shapes.medium)
        )
    }
}

@Composable
fun ExpensesDetails(
    expense: Expense,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_medium),
                vertical = dimensionResource(id = R.dimen.padding_small)
            )
    ) {
        Quantity(
            volume = expense.volume ?: 0.0,
            quantity = expense.quantity ?: 0.0,
            unit = expense.unit ?: "Kg"
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
        Amount(
            amount = expense.amount
        )
    }
}

@Composable
fun Quantity(
    volume: Double,
    quantity: Double,
    unit: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ) {
        SurfaceComplimentText(text = stringResource(id = R.string.quantity))
        SurfaceText(text = quantity.toString())
        SurfaceComplimentText(text = stringResource(id = R.string.multiply_symbol))
        SurfaceText(text = volume.toString())
        SurfaceComplimentText(text = unit)
    }
}

@Composable
fun Amount(
    amount: Double,
    modifier: Modifier = Modifier
) {
    val currency = NumberFormat.getCurrencyInstance().currency
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = modifier
        ) {
            SurfaceComplimentText(
                text = stringResource(id = R.string.per_unit_caps),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 7.sp,
                    lineHeight = 0.sp,
                    letterSpacing = 0.4.sp
                )
            )
            SurfaceComplimentText(
                text = currency?.toString() ?: stringResource(id = R.string.nrs_currency),
                style = MaterialTheme.typography.labelLarge.copy(
                    lineHeight = 0.sp
                )
            )
        }
        SurfaceText(text = amount.toString())
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ExpensesListPreview() {
//    KhaataTheme {
//        ExpensesList(
//            expensesListByCategory = fakeListOfExpenseCategory,
//            navigateToExpenseEdit = {}
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun ExpensesCardPreview() {
//    KhaataTheme {
//        ExpenseCard(
//            expense = Expenses(
//                name = "Product",
//                totalAmount = 1600.3f,
//                date = "May 25",
//                expensesDetail = ExpensesDetail(
//                    quantity = 3.5f,
//                    unit = "ltr",
//                    volume = 345.34f,
//                    amount = 5399f
//                )
//            ),
//            navigateToExpenseEdit = {}
//        )
//    }
//}
