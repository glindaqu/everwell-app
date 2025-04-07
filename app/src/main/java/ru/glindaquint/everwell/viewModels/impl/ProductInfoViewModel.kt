package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.glindaquint.everwell.network.dto.feed.ProductDto
import ru.glindaquint.everwell.uiStates.ProductInfoUiState
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel
    @Inject
    constructor() : ViewModel() {
        val product = MutableStateFlow<ProductDto?>(null)
        private val _uiState = MutableStateFlow(ProductInfoUiState())
        val uiState = _uiState.asStateFlow()

        fun searchProduct(productId: Long) {
        }

        private fun updateUiState(state: ProductInfoUiState) {
            _uiState.value = state
        }
    }
