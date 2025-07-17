package dev.ajinkyajape.kmpilot.android.di

import dev.ajinkyajape.kmpilot.news.NewsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

/**
 * Created by Ajinkya Jape on 17/07/25.
 */

val viewModelsModule = module{
    viewModel{
        NewsViewModel(get())
    }
}