package com.tashuseyin.kukacase.features.product_detail.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tashuseyin.kukacase.R
import com.tashuseyin.kukacase.common.util.CurrencyFormatter
import com.tashuseyin.kukacase.common.extension.loadImageView
import com.tashuseyin.kukacase.databinding.ProductItemBinding
import com.tashuseyin.kukacase.domain.model.OutfitsItemUIModel


class OutfitsAdapter(
    private var outfitsList: List<OutfitsItemUIModel>,
    private val onItemClickListener: (Int?) -> Unit,
) : RecyclerView.Adapter<OutfitsAdapter.OutfitsViewHolder>() {

    inner class OutfitsViewHolder(
        private val binding: ProductItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(outfits: OutfitsItemUIModel, onItemClickListener: (Int?) -> Unit) {
            binding.apply {
                productTitle.text = outfits.title
                productRatingRate.rating = outfits.ratingRate?.toFloat() ?: 0f
                productRatingCount.text = "(${outfits.ratingCount})"
                productOriginalPrice.text = CurrencyFormatter.convert(outfits.originalPrice)
                outfits.discountPrice?.let {
                    productDiscountPrice.text = CurrencyFormatter.convert(it)
                    productDiscountPrice.isVisible = true
                    productOriginalPrice.paintFlags =
                        productOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
                productImage.loadImageView(outfits.image)
                addToCart.root.isVisible = true

                addToCart.root.setOnClickListener {
                    addToCart.root.isEnabled = false
                    addToCart.root.setText(R.string.added_to_cart)
                    outfits.isAddCart = true
                    onItemClickListener.invoke(outfits.id)
                }
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

    @SuppressLint("NotifyDataSetChanged")
    fun setData(outfitsList: List<OutfitsItemUIModel>) {
        this.outfitsList = outfitsList
        notifyDataSetChanged()
    }
}
