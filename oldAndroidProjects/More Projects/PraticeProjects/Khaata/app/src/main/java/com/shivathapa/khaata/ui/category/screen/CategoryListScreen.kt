package com.shivathapa.khaata.ui.category.screen

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shivathapa.khaata.R
import com.shivathapa.khaata.data.Category
import com.shivathapa.khaata.ui.common.SurfaceComplimentText

@Composable
fun CategoryList(
    navigateToExpensesScreen: () -> Unit,
    lazyListState: LazyListState,
    contentPadding: PaddingValues,
    expenseCategory: List<Category>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = contentPadding,
        state = lazyListState,
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)),
    ) {
        items(
            items = expenseCategory,
            key = { expenseCategory -> expenseCategory.categoryId }) { category ->
            CategoryCard(
                navigateToExpensesScreen = navigateToExpensesScreen,
                category = category
            )
        }
    }
}

@Composable
fun CategoryCard(
    navigateToExpensesScreen: () -> Unit,
    category: Category,
    modifier: Modifier = Modifier
) {
    var showTotalRecords by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_small))
            .animateContentSize()
            .clickable(
                onClick = navigateToExpensesScreen
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            CategoryImage(R.drawable.farmer_milking_cow)
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .weight(1f)
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_small)
                    )
            ) {
                CategoryTitle(title = category.category)
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_tween_title_date)))
                CategoryDate(date = "Mar 23")
            }
            CategoryTotalAmount(
                onClick = { showTotalRecords = !showTotalRecords },
                totalAmount = 344.343f,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        if (showTotalRecords) {
            Divider(
                thickness = 0.2.dp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.alpha(0.5f)
            )
            DropDownDetails(
                totalRecord = 11,
                modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun CategoryImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = modifier
            .size(dimensionResource(id = R.dimen.category_card_row_image_height))
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
fun CategoryTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
fun CategoryDate(
    date: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = date,
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
            .alpha(0.7f)
    )
}

@Composable
fun CategoryTotalAmount(
    onClick: () -> Unit,
    totalAmount: Float,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = modifier
        ) {
            SurfaceComplimentText(
                text = stringResource(id = R.string.total_caps),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 9.sp,
                    lineHeight = 0.sp,
                    letterSpacing = 0.4.sp
                )
            )
            SurfaceComplimentText(
                text = totalAmount.toString(),
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
fun DropDownDetails(
    totalRecord: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.total_records),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary,
        )
        Surface(
            modifier = Modifier
                .padding(start = dimensionResource(id = R.dimen.padding_small))
                .clip(MaterialTheme.shapes.small)
        ) {
            Text(
                text = totalRecord.toString(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.details_surface_text_padding))
            )
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun CategoryScreenPreview() {
//    KhaataTheme {
//        CategoryCard(
//            navigateToExpensesScreen = {},
//            category = ExpenseCategory(
//                id = 1,
//                title = "MilkShake and other milk products",
//                expenses = Expenses(
//                    name = "Milk",
//                    totalAmount = 1600.3f,
//                    date = "Mar 11",
//                    expensesDetail = ExpensesDetail(
//                        quantity = 3.5f,
//                        unit = "ltr",
//                        volume = 2342.456f,
//                        amount = 5399f
//                    )
//                ),
//                image = R.drawable.farmer_milking_cow
//            )
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun CategoryListPreview() {
//    KhaataTheme {
//        CategoryList(
//            navigateToExpensesScreen = {},
//            lazyListState = rememberLazyListState(),
//            expenseCategory = fakeListOfExpenseCategory,
//            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium))
//        )
//    }
//}
