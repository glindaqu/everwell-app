package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.network.dto.feed.FeedDto
import ru.glindaquint.everwell.services.FeedService
import ru.glindaquint.everwell.uiStates.FeedUiState
import ru.glindaquint.everwell.uiStates.Feeds
import ru.glindaquint.everwell.utils.FeedType
import ru.glindaquint.everwell.utils.isSameDay
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FeedViewModel
    @Inject
    constructor(
        private val feedService: FeedService,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(FeedUiState(loading = true))
        val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

        init {
            loadFeeds()
        }

        private fun loadFeeds() {
            _uiState.update { it.copy(loading = true, error = null) }

            feedService.refreshFeeds(onSuccess = {
                viewModelScope.launch {
                    feedService.feeds.collect { feeds ->
                        val todayFeeds =
                            feeds.filter { feed ->
                                isSameDay(feed.feedDate, Date())
                            }
                        calculateAndUpdateState(todayFeeds)
                    }
                }
            }, onFailure = { e ->
                _uiState.update { currentState ->
                    currentState.copy(
                        loading = false,
                        error = e.message ?: "Unknown error occurred",
                    )
                }
            })
        }

        private fun calculateAndUpdateState(todayFeeds: List<FeedDto>) {
            val allProducts = todayFeeds.flatMap { it.feedProducts }

            _uiState.update { currentState ->
                currentState.copy(
                    feeds = mapFeedsByType(todayFeeds),
                    totalCalories = allProducts.sumOf { it.calories * it.quantity },
                    totalProtein = allProducts.sumOf { it.protein * it.quantity },
                    totalFat = allProducts.sumOf { it.fat * it.quantity },
                    totalCarbohydrates = allProducts.sumOf { it.carbohydrates * it.quantity },
                    loading = false,
                    error = null,
                )
            }
        }

        private fun mapFeedsByType(feeds: List<FeedDto>): Feeds {
            fun getProducts(type: FeedType): List<String> =
                feeds
                    .filter { it.feedType == type }
                    .flatMap { it.feedProducts }
                    .mapNotNull { it.product?.title }

            return Feeds(
                breakfast = getProducts(FeedType.BREAKFAST),
                lunch = getProducts(FeedType.LUNCH),
                dinner = getProducts(FeedType.DINNER),
                snack = getProducts(FeedType.SNACK),
            )
        }
    }
