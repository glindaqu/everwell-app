package ru.glindaquint.everwell.network.dto.feed

import ru.glindaquint.everwell.network.dto.feedProducts.FeedProductDto
import ru.glindaquint.everwell.utils.FeedType
import java.util.Date

data class FeedDto(
    val feedId: Long,
    val feedDate: Date,
    val feedType: FeedType,
    val feedProducts: List<FeedProductDto>,
)
