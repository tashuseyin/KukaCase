package com.tashuseyin.kukacase.features.product_detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tashuseyin.kukacase.R
import com.tashuseyin.kukacase.common.util.CurrencyFormatter
import com.tashuseyin.kukacase.common.util.loadImageView
import com.tashuseyin.kukacase.databinding.ProductItemBinding
import com.tashuseyin.kukacase.domain.model.OutfitsItemUIModel


class OutfitsAdapter(
    private val outfitsList: List<OutfitsItemUIModel>,
    private val onItemClickListener: (Int?) -> Unit
) : RecyclerView.Adapter<OutfitsAdapter.OutfitsViewHolder>() {

    class OutfitsViewHolder(
        private val binding: ProductItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(outfits: OutfitsItemUIModel, onItemClickListener: (Int?) -> Unit) {
            binding.productTitle.text = outfits.title
            binding.productRatingRate.rating = outfits.ratingRate?.toFloat() ?: 0f
            binding.productRatingCount.text = "(${outfits.ratingCount})"
            binding.productOriginalPrice.text = CurrencyFormatter.convert(outfits.originalPrice)
            binding.productImage.loadImageView(outfits.image)
            binding.addToCart.root.isVisible = true

            binding.addToCart.root.setOnClickListener {
                binding.addToCart.root.isEnabled = false
                binding.addToCart.root.setText(R.string.added_to_cart)

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
