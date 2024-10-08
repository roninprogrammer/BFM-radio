package com.bfmradio.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bfmradio.app.ui.screens.PodcastScreen
import com.bfmradio.app.ui.theme.BFMTheme
import com.bfmradio.app.ui.viewmodels.PodcastViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // This is entry point of BFM application where main Compose UI being setup
            val podcastViewModel: PodcastViewModel by viewModels() // Initialize ViewModel properly

            BFMTheme {
                PodcastScreen(viewModel = podcastViewModel)
            }
        }
    }
}

