package dev.ajinkyajape.kmpilot.news

/**
 * Created by Ajinkya Jape on 15/07/25.
 */

data class NewsState(
    val newsList : List<NewsModel>? = listOf(),
    val bLoading : Boolean = false,
    val sError : String? = null
)