package com.example.expensetracker.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.databinding.ActivityMainBinding
import com.example.expensetracker.ui.expense.AddExpenseActivity
import com.example.expensetracker.ui.expense.EditExpenseActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setupListeners()
        observeData()
    }

    private fun setupRecycler() {
        adapter = ExpenseAdapter(emptyList(),
            onDelete = { viewModel.delete(it) },
            onEdit = { openEdit(it.id) })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnAddExpense.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }
    }

    private fun observeData() {
        viewModel.expenses.observe(this) {
            adapter.update(it)
        }
    }

    private fun openEdit(id: Int) {
        val i = Intent(this, EditExpenseActivity::class.java)
        i.putExtra("expense_id", id)
        startActivity(i)
    }
}
