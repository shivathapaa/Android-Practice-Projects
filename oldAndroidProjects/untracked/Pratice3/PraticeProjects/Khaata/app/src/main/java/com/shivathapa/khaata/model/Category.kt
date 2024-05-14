package com.shivathapa.khaata.model

import androidx.annotation.DrawableRes

data class ExpenseCategory(
    val id: Int,
    @DrawableRes val image: Int,
    val title: String,
    val expenses: Expenses
    )


data class Expenses(
    val name: String,
    val date: String,
    val totalAmount: Float,
    val expensesDetail: ExpensesDetail
)

data class ExpensesDetail(
    val volume: Float,
    val quantity: Float,
    val unit: String,
    val amount: Float
)

