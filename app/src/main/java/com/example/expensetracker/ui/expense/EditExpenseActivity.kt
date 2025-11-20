package com.example.expensetracker.ui.expense

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.data.db.ExpenseDatabase
import com.example.expensetracker.data.model.Expense
import com.example.expensetracker.databinding.ActivityEditExpenseBinding
import kotlinx.coroutines.*

class EditExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditExpenseBinding
    private var expenseId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        expenseId = intent.getIntExtra("expense_id", 0)

        loadExpense()

        binding.btnUpdate.setOnClickListener { updateExpense() }
    }

    private fun loadExpense() {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = ExpenseDatabase.getDatabase(this@EditExpenseActivity).expenseDao()
            val expense = dao.getExpenseById(expenseId)

            withContext(Dispatchers.Main) {
                binding.etTitle.setText(expense.title)
                binding.etAmount.setText(expense.amount.toString())
                binding.etCategory.setText(expense.category)
            }
        }
    }

    private fun updateExpense() {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = ExpenseDatabase.getDatabase(this@EditExpenseActivity).expenseDao()

            dao.insert(
                Expense(
                    id = expenseId,
                    title = binding.etTitle.text.toString(),
                    amount = binding.etAmount.text.toString().toDouble(),
                    category = binding.etCategory.text.toString(),
                    date = System.currentTimeMillis()
                )
            )
            finish()
        }
    }
}
