package com.example.expensetracker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.data.model.Expense
import com.example.expensetracker.databinding.ItemExpenseBinding
import java.text.SimpleDateFormat
import java.util.*

class ExpenseAdapter(
    private var list: List<Expense>,
    private val onDelete: (Expense) -> Unit,
    private val onEdit: (Expense) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseVH>() {

    inner class ExpenseVH(val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseVH {
        val binding = ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExpenseVH(binding)
    }

    override fun onBindViewHolder(holder: ExpenseVH, position: Int) {
        val item = list[position]
        val b = holder.binding

        b.tvTitle.text = item.title
        b.tvAmount.text = "₹${item.amount}"
        b.tvCategory.text = item.category

        b.tvDate.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            .format(Date(item.date))

        b.btnDelete.setOnClickListener { onDelete(item) }
        b.btnEdit.setOnClickListener { onEdit(item) }
    }

    override fun getItemCount() = list.size

    fun update(newList: List<Expense>) {
        list = newList
        notifyDataSetChanged()
    }
}
