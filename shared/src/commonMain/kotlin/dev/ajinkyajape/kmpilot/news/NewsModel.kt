package dev.ajinkyajape.kmpilot.news

/**
 * Created by Ajinkya Jape on 15/07/25.
 */

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsModel(
    @SerialName("title")
    val sNewsTitle: String,

    @SerialName("description")
    val sNewsDesc: String?,

    @SerialName("publishedAt")
    val sNewsDate: String,

    @SerialName("urlToImage")
    val sNewsImgUrl: String?
)
