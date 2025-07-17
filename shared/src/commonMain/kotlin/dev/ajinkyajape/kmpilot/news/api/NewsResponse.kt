package dev.ajinkyajape.kmpilot.news.api

/**
 * Created by Ajinkya Jape on 17/07/25.
 */

import dev.ajinkyajape.kmpilot.news.NewsModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("status")
    val status: String,

    @SerialName("totalResults")
    val results: Int,

    @SerialName("articles")
    val newsDataList: List<NewsModel>
)
