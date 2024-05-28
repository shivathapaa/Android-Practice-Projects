package com.shivathapa.khaata.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.shivathapa.khaata.KhaataApplication
import com.shivathapa.khaata.ui.category.entry.CategoryEntryViewModel
import com.shivathapa.khaata.ui.expenses.entry.ExpensesEntryViewModel
import com.shivathapa.khaata.ui.expenses.screen.ExpenseListViewModel
import com.shivathapa.khaata.ui.home.HomeScreenViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            CategoryEntryViewModel(
                khaataApplication().container.categoryRepository
            )
        }

        initializer {
            HomeScreenViewModel(
                khaataApplication().container.categoryRepository
            )
        }

        initializer {
            ExpensesEntryViewModel(
                khaataApplication().container.expenseRepository
            )
        }

        initializer {
            ExpenseListViewModel(
                this.createSavedStateHandle(),
                khaataApplication().container.expenseRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [KhaataApplication].
 */
fun CreationExtras.khaataApplication(): KhaataApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as KhaataApplication)