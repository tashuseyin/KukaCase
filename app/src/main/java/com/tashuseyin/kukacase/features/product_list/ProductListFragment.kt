package com.tashuseyin.kukacase.features.product_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tashuseyin.kukacase.databinding.FragmentProductListBinding
import com.tashuseyin.kukacase.features.product_list.adapter.ProductListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private lateinit var productListAdapter: ProductListAdapter
    private val productListViewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
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

        binding.sortIcon.setOnClickListener {
            productListViewModel.updateSort(productListViewModel.sort)
        }
    }

    private fun collectUIState() {
        lifecycleScope.launch {
            productListViewModel.uiState.collect { state ->
                binding.progressCircular.isVisible = state.isLoading
                if (state.category.isNotEmpty()) {
                    binding.productListToolbarTitle.text = state.category
                }
                if (state.productList.isNotEmpty()) {
                    productListAdapter = ProductListAdapter(state.productList) { id ->
                        findNavController().navigate(
                            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                                id
                            )
                        )
                    }
                    binding.productListRecyclerview.adapter = productListAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}