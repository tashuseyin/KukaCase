package com.tashuseyin.kukacase.features.product_detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tashuseyin.kukacase.common.util.CurrencyFormatter
import com.tashuseyin.kukacase.common.util.loadImageView
import com.tashuseyin.kukacase.databinding.FragmentProductDetailBinding
import com.tashuseyin.kukacase.features.product_detail.adapter.OutfitsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var outfitsAdapter: OutfitsAdapter
    private val productDetailViewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewClickListener()
        collectUIState()
    }

    private fun onViewClickListener() {
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun collectUIState() {
        lifecycleScope.launch {
            productDetailViewModel.uiState.collect { state ->
                binding.progressCircular.isVisible = state.isLoading
                binding.detailCenterLayout.isVisible = state.isLoading.not()
                state.productItemUIModel?.let {
                    binding.productDescription.text = it.description
                    binding.productImage.loadImageView(it.image)
                    binding.productTitle.text = it.title
                    binding.productRatingRate.rating = it.ratingRate?.toFloat() ?: 0f
                    binding.productRatingCount.text = "(${it.ratingCount})"
                    binding.productPrice.text = CurrencyFormatter.convert(it.price)
                    binding.productDetailToolbarTitle.text = it.title
                }
                if (state.outfitsList.isNotEmpty()) {
                    outfitsAdapter = OutfitsAdapter(state.outfitsList) {}
                    binding.productDetailRecyclerview.adapter = outfitsAdapter
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}