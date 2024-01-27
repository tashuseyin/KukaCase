package com.tashuseyin.kukacase.features.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tashuseyin.kukacase.databinding.FragmentCategoriesBinding
import com.tashuseyin.kukacase.features.categories.adapter.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val categoriesViewModel: CategoriesViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUIState()
    }

    private fun collectUIState() {
        lifecycleScope.launch {
            categoriesViewModel.uiState.collect { state ->
                if (state.categoriesList.isNotEmpty()) {
                    categoriesAdapter = CategoriesAdapter(state.categoriesList) { category ->
                        findNavController().navigate(
                            CategoriesFragmentDirections.actionCategoriesFragmentToProductListFragment(
                                category
                            )
                        )
                    }
                    binding.categoriesRecyclerview.adapter = categoriesAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}