package com.shivathapa.khaata.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val categoryId: Int,
    val category: String,
//    val totalAmount: Double,
//    @Embedded val date: Date
)

@Entity(
    tableName = "expense",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("expenseCategoryId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Expense(
    @PrimaryKey(autoGenerate = true) val expenseId: Int,
    val expenseCategoryId: Int,
//    val category: String,
    val item: String,
    val currency: String,
    val volume: Double?,
    val quantity: Double?,
    val unit: String?,
    val amount: Double,
    @Embedded val date: String
)

data class CategoryWithExpenses(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "expenseCategoryId"
    )
    val expenses: List<Expense>
)


