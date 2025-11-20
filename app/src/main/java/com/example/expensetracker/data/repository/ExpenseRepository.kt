package com.example.expensetracker.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.expensetracker.data.db.ExpenseDatabase
import com.example.expensetracker.data.model.Expense

class ExpenseRepository(context: Context) {

    private val dao = ExpenseDatabase.getDatabase(context).expenseDao()

    fun getAllExpenses(): LiveData<List<Expense>> = dao.getAllExpenses()

    suspend fun addExpense(expense: Expense) = dao.insert(expense)

    suspend fun deleteExpense(expense: Expense) = dao.delete(expense)

    suspend fun getExpenseById(id: Int) = dao.getExpenseById(id)

    suspend fun filterByRange(start: Long, end: Long) = dao.filterByDateRange(start, end)
}
