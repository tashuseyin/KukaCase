package com.tashuseyin.kukacase.features.product_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tashuseyin.kukacase.common.util.CurrencyFormatter
import com.tashuseyin.kukacase.common.util.loadImageView
import com.tashuseyin.kukacase.databinding.ProductItemBinding
import com.tashuseyin.kukacase.domain.model.ProductItemUIModel

class ProductListAdapter(
    private val productList: List<ProductItemUIModel>,
    private val onItemClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    class ProductListViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(productItem: ProductItemUIModel, onItemClickListener: (Int) -> Unit) {
            binding.productTitle.text = productItem.title
            binding.productRatingRate.rating = productItem.ratingRate?.toFloat() ?: 0f
            binding.productRatingCount.text = "(${productItem.ratingCount})"
            binding.productOriginalPrice.text = CurrencyFormatter.convert(productItem.price)
            binding.productImage.loadImageView(productItem.image)

            binding.productItem.setOnClickListener {
                productItem.id?.let { onItemClickListener.invoke(it) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
       holder.bind(productList[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}