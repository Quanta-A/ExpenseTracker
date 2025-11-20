package com.example.expensetracker.api

import com.example.expensetracker.data.model.Expense
import com.example.expensetracker.data.repository.ExpenseRepository

class ExpenseApi(private val repo: ExpenseRepository) {

    suspend fun add(expense: Expense) = repo.addExpense(expense)

    // You do NOT have an update function, so remove or create it
    // suspend fun update(expense: Expense) = repo.updateExpense(expense)

    suspend fun delete(expense: Expense) = repo.deleteExpense(expense)

    suspend fun list(start: Long, end: Long) = repo.filterByRange(start, end)

    // You do NOT have search() in repository → remove unless added in DAO
    // fun search(value: String) = repo.search(value)
}
