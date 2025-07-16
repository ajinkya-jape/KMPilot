package dev.ajinkyajape.kmpilot.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ajinkyajape.kmpilot.Platform

/**
 * Created by Ajinkya Jape on 15/07/25.
 */

@Composable
fun DeviceInfoScreen(
    onUpBackPress:()->Unit
) {
    Column {
        AppTitleBar(onUpBackPress)
        AppContentView()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTitleBar(
    onUpBackPress:()->Unit
) {
    TopAppBar(
        title = {
            Text(text = "About Device", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        },
        navigationIcon = {
            IconButton(onClick = onUpBackPress) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Up Button",
                )
            }
        }
    )
}

@Composable
fun AppContentView() {
    val rowDataList = fetchRowItem()


    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        shape = RoundedCornerShape(9.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(rowDataList) { row ->
                    RowItems(row.first, row.second)
                }
            }
        }
    }
}


@Composable
fun RowItems(
    sTitle: String,
    sSubTitle: String
) {

    Box {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = sTitle,
                fontSize = 13.sp,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black
            )
            Text(
                text = sSubTitle,
                fontSize = 11.sp,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }


}

private fun fetchRowItem(): List<Pair<String, String>> {
    val mPlatform = Platform().apply {
        getDeviceInfo()
    }

    return listOf(
        "OS Name" to mPlatform.osName,
        "OS Version" to mPlatform.osVersion,
        "Device Model" to mPlatform.deviceModel,
        "Density" to mPlatform.density.toString()
    )
}