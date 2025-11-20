package com.example.expensetracker.ui.expense

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.data.db.ExpenseDatabase
import com.example.expensetracker.data.model.Expense
import com.example.expensetracker.databinding.ActivityAddExpenseBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val expense = Expense(
                title = binding.etTitle.text.toString(),
                amount = binding.etAmount.text.toString().toDouble(),
                category = binding.etCategory.text.toString(),
                date = System.currentTimeMillis()
            )

            CoroutineScope(Dispatchers.IO).launch {
                ExpenseDatabase.getDatabase(this@AddExpenseActivity)
                    .expenseDao()
                    .insert(expense)
                finish()
            }
        }
    }
}
