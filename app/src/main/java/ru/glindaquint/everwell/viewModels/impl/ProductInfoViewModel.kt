package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.glindaquint.everwell.services.ProductService
import ru.glindaquint.everwell.uiStates.ProductInfoUiState
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel
    @Inject
    constructor(
        private val productService: ProductService,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(ProductInfoUiState())
        val uiState = _uiState.asStateFlow()

        fun loadProduct(productId: Long) {
            val product = productService.allProducts.value.find { it.productId == productId }
            updateUiState(
                _uiState.value.copy(
                    title = product?.title ?: "",
                    fats = product?.fat.toString(),
                    calories = product?.calories.toString(),
                    protein = product?.protein.toString(),
                    carbohydrates = product?.carbohydrates.toString(),
                    portionSize = product?.weightInGrams.toString(),
                ),
            )
        }

        private fun updateUiState(state: ProductInfoUiState) {
            _uiState.value = state
        }
    }
