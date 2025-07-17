package dev.ajinkyajape.kmpilot.android

import android.app.Application
import dev.ajinkyajape.kmpilot.android.di.viewModelsModule
import dev.ajinkyajape.kmpilot.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Ajinkya Jape on 17/07/25.
 */
class KMPilotApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        val diModules = sharedModule + viewModelsModule
        startKoin{
            androidContext(this@KMPilotApp)
            modules(diModules)
        }
    }
}