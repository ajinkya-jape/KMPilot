package dev.ajinkyajape.kmpilot.di

import dev.ajinkyajape.kmpilot.news.di.newModules

/**
 * Created by Ajinkya Jape on 17/07/25.
 */
val sharedModule = listOf(
    newModules,
    apiServicesModule
)