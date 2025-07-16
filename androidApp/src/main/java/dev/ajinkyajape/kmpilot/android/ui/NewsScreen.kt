package dev.ajinkyajape.kmpilot.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.ajinkyajape.kmpilot.news.NewsModel
import dev.ajinkyajape.kmpilot.news.NewsViewModel

/**
 * Created by Ajinkya Jape on 15/07/25.
 */

@Composable
fun NewsScreen(
    onAboutButtonClick: () -> Unit,
    newsViewModel: NewsViewModel,
) {

    val articlesState = newsViewModel.newsState.collectAsState()

    Column {
        AppBar(onAboutButtonClick)

        if (articlesState.value.bLoading)
            Loader()
        if (articlesState.value.sError != null)
            ErrorMessage(articlesState.value.sError!!)
        if (articlesState.value.newsList!!.isNotEmpty())
            NewsListView(newsViewModel.newsState.value.newsList!!)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onAboutButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "News") },
        actions = {
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Device",
                )
            }
        }
    )
}

@Composable
fun NewsListView(newsList: List<NewsModel>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(newsList) { article ->
            NewsItemView(news = article)
        }
    }
}

@Composable
fun NewsItemView(news: NewsModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = news.sNewsImgUrl,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = news.sNewsTitle,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = news.sNewsDesc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = news.sNewsDate,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}