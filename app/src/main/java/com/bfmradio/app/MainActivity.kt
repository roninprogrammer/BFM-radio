package com.bfmradio.app

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.bfmradio.app.ui.screens.PodcastScreen
import com.bfmradio.app.ui.theme.BFMTheme
import com.bfmradio.app.ui.viewmodels.PodcastViewModel
import com.bfmradio.app.utils.ConnectivityObserver
import com.bfmradio.app.utils.Utils


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val podcastViewModel: PodcastViewModel by viewModels()
        val connectivityObserver = ConnectivityObserver(applicationContext)

        setContent {
            BFMTheme {
                var isOnline by remember { mutableStateOf(true) }
                var snackbarVisible by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    connectivityObserver.observeNetworkStatus().collect { status ->
                        isOnline = status
                        snackbarVisible = !isOnline
                    }
                }


                PodcastScreen(viewModel = podcastViewModel)
                if (snackbarVisible) {
                    Snackbar(
                        action = {
                            Button(onClick = {
                                val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                                startActivity(intent)
                            }) {
                                Text(Utils.settings())
                            }
                        }
                    ) {
                        Text(Utils.lostConnection())
                    }
                }
            }
        }
    }
}


