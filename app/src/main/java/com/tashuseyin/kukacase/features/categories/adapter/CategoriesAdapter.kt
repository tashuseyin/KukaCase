package com.tashuseyin.kukacase.features.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tashuseyin.kukacase.databinding.CategoryItemBinding

class CategoriesAdapter(
    private val categoriesList: List<String>,
    private val onItemClickListener: (String) -> Unit,
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    class CategoriesViewHolder(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String, onItemClickListener: (String) -> Unit) {
            binding.categoryName.text = category

            binding.categoryItem.setOnClickListener {
                onItemClickListener.invoke(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoriesList[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}