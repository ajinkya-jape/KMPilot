package dev.ajinkyajape.kmpilot.news.di

import dev.ajinkyajape.kmpilot.news.NewsViewModel
import dev.ajinkyajape.kmpilot.news.api.ApiServices
import dev.ajinkyajape.kmpilot.news.api.NewsUseCase
import org.koin.dsl.module

/**
 * Created by Ajinkya Jape on 17/07/25.
 */


val newModules = module {
    single<ApiServices> {ApiServices(get())}
    single<NewsUseCase> { NewsUseCase(get()) }
    single<NewsViewModel> {NewsViewModel(get())}
}