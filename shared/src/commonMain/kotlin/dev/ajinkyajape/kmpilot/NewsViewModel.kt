package dev.ajinkyajape.kmpilot

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

/**
 * Created by Ajinkya Jape on 15/07/25.
 */
class NewsViewModel: BaseViewModel() {

    private val _newState: MutableStateFlow<NewsState> = MutableStateFlow(NewsState(bLoading = true))
    val newsState: StateFlow<NewsState> get() = _newState

    private val newsUseCase : NewsUseCase
    init {

        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        val service = ApiServices(httpClient)
        newsUseCase = NewsUseCase(service)
        getNews()
    }

    // For iOS
    fun observeNewsState(callback: (NewsState) -> Unit): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            newsState.collect {
                callback(it)
            }
        }
    }

    private fun getNews(){
        scope.launch {
            val fetchedNewsData = newsUseCase.fetchNews()
            _newState.emit(NewsState(newsList = fetchedNewsData))
        }

    }
}