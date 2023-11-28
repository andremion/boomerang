package io.github.andremion.boomerang.sample.android.ui.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.andremion.boomerang.sample.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        val configuration = LocalConfiguration.current
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Row(
                    modifier = Modifier.padding(innerPadding),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    WaterCounter(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        snackbarHostState = snackbarHostState
                    )
                    TasksList(
                        modifier = Modifier.fillMaxWidth(),
                        snackbarHostState = snackbarHostState
                    )
                }
            }

            else -> {
                Column(
                    modifier = Modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    WaterCounter(
                        modifier = Modifier.fillMaxWidth(),
                        snackbarHostState = snackbarHostState
                    )
                    TasksList(
                        modifier = Modifier.fillMaxWidth(),
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }
    }
}
