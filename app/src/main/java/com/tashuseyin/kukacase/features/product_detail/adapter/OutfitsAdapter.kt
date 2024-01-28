package com.tashuseyin.kukacase.features.product_detail.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tashuseyin.kukacase.common.util.CurrencyFormatter
import com.tashuseyin.kukacase.common.util.loadImageView
import com.tashuseyin.kukacase.data.model.outfits.Outfits
import com.tashuseyin.kukacase.databinding.ProductItemBinding
import com.tashuseyin.kukacase.domain.model.OutfitsItemUIModel
import com.tashuseyin.kukacase.domain.model.ProductItemUIModel


class OutfitsAdapter(
    private val outfitsList: List<OutfitsItemUIModel>,
    private val onItemClickListener: () -> Unit,
) : RecyclerView.Adapter<OutfitsAdapter.OutfitsViewHolder>() {

    class OutfitsViewHolder(
        private val binding: ProductItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(outfits: OutfitsItemUIModel, onItemClickListener: () -> Unit) {
            binding.productTitle.text = outfits.title
            binding.productRatingRate.rating = outfits.ratingRate?.toFloat() ?: 0f
            binding.productRatingCount.text = "(${outfits.ratingCount})"
            binding.productPrice.text = CurrencyFormatter.convert(outfits.originalPrice)
            binding.productImage.loadImageView(outfits.image)
            binding.addToCart.root.isVisible = true

            binding.addToCart.root.setOnClickListener {
                binding.addToCart.root.isEnabled = false
                binding.productPrice.paintFlags =  binding.productPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutfitsViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OutfitsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OutfitsViewHolder, position: Int) {
        holder.bind(outfitsList[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return outfitsList.size
    }
}