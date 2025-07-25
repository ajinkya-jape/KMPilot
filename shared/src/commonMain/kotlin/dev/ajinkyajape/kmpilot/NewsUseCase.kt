package dev.ajinkyajape.kmpilot

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

/**
 * Created by Ajinkya Jape on 17/07/25.
 */
class NewsUseCase(private val apiServices: ApiServices) {

    suspend fun fetchNews() : List<NewsModel>{
        val newsData = apiServices.getNewsServices()
        return mapNewsToView(newsData)
    }

    private fun mapNewsToView(listData : List<NewsModel>) : List<NewsModel> = listData.map{ results ->
        NewsModel(
            results.sNewsTitle,
            results.sNewsDesc ?: "Learn More..",
            getDaysAgoString(results.sNewsDate),
            results.sNewsImgUrl
        )

    }

    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}