package dev.ajinkyajape.kmpilot.news

import dev.ajinkyajape.kmpilot.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by Ajinkya Jape on 15/07/25.
 */
class NewsViewModel : BaseViewModel() {

    private val _newState: MutableStateFlow<NewsState> = MutableStateFlow(NewsState(bLoading = true))
    val newsState: StateFlow<NewsState> get() = _newState

    init {
        getNews()
    }

    fun observeNewsState(callback: (NewsState) -> Unit): Job {
        return CoroutineScope(Dispatchers.Main).launch {
            newsState.collect {
                callback(it)
            }
        }
    }

    private fun getNews(){
        scope.launch {
            delay(2000)
            _newState.emit(NewsState(sError= "Somthing went wrong!!"))

            delay(2000)
            _newState.emit(NewsState(newsList = fetchNews()))
        }

    }

    private suspend fun fetchNews(): List<NewsModel> = mockNews

    private val mockNews = listOf(
        NewsModel(
            "Stock market today: Live updates - CNBC",
            "Futures were higher in premarket trading as Wall Street tried to regain its footing.",
            "2023-11-09",
            "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
        ),
        NewsModel(
            "Best iPhone Deals (2023): Carrier Deals, Unlocked iPhones",
            "Apple's smartphones rarely go on sale, but if you’re looking to upgrade (or you're gift shopping), here are a few cost-saving options.",
            "2023-11-09",
            "https://media.wired.com/photos/622aa5c8cca6acf55fb70b57/191:100/w_1280,c_limit/iPhone-13-Pro-Colors-SOURCE-Apple-Gear.jpg",
        ),
        NewsModel(
            "Samsung details ‘Galaxy AI’ and a feature that can translate phone calls in real time",
            "In a new blog post, Samsung previewed what it calls “a new era of Galaxy AI” coming to its smartphones and detailed a feature that will use artificial intelligence to translate phone calls in real time.",
            "2023-11-09",
            "https://cdn.vox-cdn.com/thumbor/Ocz_QcxUdtaexp1pPTMygaqzbR8=/0x0:2000x1333/1200x628/filters:focal(1000x667:1001x668)/cdn.vox-cdn.com/uploads/chorus_asset/file/24396795/DSC04128_processed.jpg",
        ),
    )
}