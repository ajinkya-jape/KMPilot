package dev.ajinkyajape.kmpilot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

/**
 * Created by Ajinkya Jape on 15/07/25.
 */

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class BaseViewModel: ViewModel() {
  actual  val scope = viewModelScope
}