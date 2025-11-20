package com.example.expensetracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.expensetracker.data.model.Expense

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): LiveData<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expenses WHERE date BETWEEN :start AND :end ORDER BY date DESC")
    suspend fun filterByDateRange(start: Long, end: Long): List<Expense>

    @Query("SELECT * FROM expenses WHERE id = :id")
    suspend fun getExpenseById(id: Int): Expense
}
