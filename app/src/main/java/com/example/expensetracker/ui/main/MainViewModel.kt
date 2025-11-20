package com.example.expensetracker.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.example.expensetracker.data.model.Expense
import com.example.expensetracker.data.repository.ExpenseRepository
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = ExpenseRepository(app)

    val expenses: LiveData<List<Expense>> = repo.getAllExpenses()

    fun delete(expense: Expense) = viewModelScope.launch {
        repo.deleteExpense(expense)
    }

    fun filterToday() = viewModelScope.launch {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        val start = cal.timeInMillis

        cal.set(Calendar.HOUR_OF_DAY, 23)
        val end = cal.timeInMillis

        _filtered.value = repo.filterByRange(start, end)
    }

    private val _filtered = MutableLiveData<List<Expense>>()
    val filtered: LiveData<List<Expense>> get() = _filtered
}
