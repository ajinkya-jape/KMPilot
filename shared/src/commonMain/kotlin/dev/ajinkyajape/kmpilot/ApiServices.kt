package dev.ajinkyajape.kmpilot

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Created by Ajinkya Jape on 17/07/25.
 */

class ApiServices(private val httpClient: HttpClient) {

    val BASE_URL : String = "https://newsapi.org/v2/top-headlines?country"

    private val country = "us"
    private val category = "business"
    private val apiKey = "1504972a504544dcab569f149b57091f"

    private fun getBaseUrl(
        country: String,
        category: String,
        apiKey: String
    ):String{
        return "$BASE_URL?country=$country&category=$category&apiKey=$apiKey"
    }

    suspend fun getNewsServices() : List<NewsModel>{
        val response : NewsResponse = httpClient.get(getBaseUrl(
            country= country,
            category = category,
            apiKey= apiKey
        )).body()
        return response.newsDataList
    }
}