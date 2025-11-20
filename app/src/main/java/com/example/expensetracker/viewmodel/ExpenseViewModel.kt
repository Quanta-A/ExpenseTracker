package com.example.expensetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.db.ExpenseDatabase
import com.example.expensetracker.data.model.Expense
import com.example.expensetracker.data.repository.ExpenseRepository
import kotlinx.coroutines.launch

class ExpenseViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = ExpenseRepository(app)

    val allExpenses: LiveData<List<Expense>> = repo.getAllExpenses()

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repo.addExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repo.deleteExpense(expense)
        }
    }

    suspend fun getExpenseById(id: Int) = repo.getExpenseById(id)

    suspend fun filterExpenses(start: Long, end: Long) =
        repo.filterByRange(start, end)
}
