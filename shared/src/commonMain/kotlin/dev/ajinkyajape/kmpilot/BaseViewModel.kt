package dev.ajinkyajape.kmpilot

import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ajinkya Jape on 15/07/25.
 */

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect open class BaseViewModel() {
    val scope : CoroutineScope
}