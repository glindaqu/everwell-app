package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
        init {
            feedService.refreshFeeds(onSuccess = {
                viewModelScope.launch {
                    feedService.feeds.collect { feeds ->
                        val todayFeeds = feeds.filter { isSameDay(it.feedDate, Date()) }
                        val totalCalories =
                            todayFeeds.flatMap { it.feedProducts }.sumOf {
                                it.portionSize * it.quantity * (it.product?.calories ?: 0)
                            }
                        val totalProtein =
                            todayFeeds.flatMap { it.feedProducts }.sumOf { it.fat.toDouble() }
                        val totalFat =
                            todayFeeds.flatMap { it.feedProducts }.sumOf { it.fat.toDouble() }
                        val totalCarbohydrates =
                            todayFeeds
                                .flatMap { it.feedProducts }
                                .sumOf { it.carbohydrates.toDouble() }

                        updateUiState(
                            _uiState.value.copy(
                                feeds =
                                    Feeds(
                                        breakfast =
                                            todayFeeds
                                                .filter { it.feedType == FeedType.BREAKFAST }
                                                .flatMap {
                                                    it.feedProducts
                                                }.map {
                                                    it.product?.title.toString()
                                                },
                                        lunch =
                                            todayFeeds
                                                .filter { it.feedType == FeedType.LUNCH }
                                                .flatMap {
                                                    it.feedProducts
                                                }.map {
                                                    it.product?.title.toString()
                                                },
                                        dinner =
                                            todayFeeds
                                                .filter { it.feedType == FeedType.DINNER }
                                                .flatMap {
                                                    it.feedProducts
                                                }.map {
                                                    it.product?.title.toString()
                                                },
                                        snack =
                                            todayFeeds
                                                .filter { it.feedType == FeedType.SNACK }
                                                .flatMap {
                                                    it.feedProducts
                                                }.map {
                                                    it.product?.title.toString()
                                                },
                                    ),
                                totalCalories = totalCalories,
                                totalFat = totalFat,
                                totalProtein = totalProtein,
                                totalCarbohydrates = totalCarbohydrates,
                            ),
                        )
                    }
                }
            })
        }

        private val _uiState = MutableStateFlow(FeedUiState())
        val uiState = _uiState.asStateFlow()

        private fun updateUiState(state: FeedUiState) {
            _uiState.value = state
        }
    }
