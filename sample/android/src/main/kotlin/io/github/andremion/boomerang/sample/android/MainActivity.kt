package io.github.andremion.boomerang.sample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import io.github.andremion.boomerang.sample.android.ui.main.MainScreen
import io.github.andremion.boomerang.sample.android.ui.theme.BoomerangTheme
import moe.tlaster.precompose.PreComposeApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoomerangTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PreComposeApp {
                        MainScreen()
                    }
                }
            }
        }
    }
}
