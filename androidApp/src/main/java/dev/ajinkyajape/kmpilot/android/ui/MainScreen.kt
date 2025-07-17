package dev.ajinkyajape.kmpilot.android.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.ajinkyajape.kmpilot.android.utils.EScreens
import dev.ajinkyajape.kmpilot.news.NewsViewModel

/**
 * Created by Ajinkya Jape on 15/07/25.
 */

@Composable
fun MainScreen () {

    val navController = rememberNavController()

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = EScreens.NEWS.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            composable(EScreens.NEWS.route) {
                NewsScreen(
                    onAboutButtonClick = { navController.navigate(EScreens.ABOUT.route) }
                )
            }

            composable(EScreens.ABOUT.route) {
                DeviceInfoScreen(
                    onUpBackPress = { navController.popBackStack() }
                )
            }
        }
    }

}