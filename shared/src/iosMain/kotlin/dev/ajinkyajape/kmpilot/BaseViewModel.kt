package dev.ajinkyajape.kmpilot

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

/**
 * Created by Ajinkya Jape on 15/07/25.
 */

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class BaseViewModel {
    actual val scope : CoroutineScope = CoroutineScope(Dispatchers.IO)
    fun clear(){
        scope.cancel()
    }
}