package dev.ajinkyajape.kmpilot.di

import dev.ajinkyajape.kmpilot.news.NewsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

/**
 * Created by Ajinkya Jape on 17/07/25.
 */


fun initKoin() {
    val diModules = sharedModule
    startKoin { modules(diModules) }
}

class NewsInjector : KoinComponent {
    val newsViewModel : NewsViewModel by inject()
}