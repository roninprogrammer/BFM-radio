package com.bfmradio.app.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ConnectivityObserver(context: Context) {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    fun observeNetworkStatus(): Flow<Boolean> = callbackFlow {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(true)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

    private fun determineNetworkStatus(capabilities: NetworkCapabilities?): NetworkStatus {
        return when {
            capabilities == null -> NetworkStatus.Unknown
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) -> {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)) {
                        NetworkStatus.GoodWifi
                    } else {
                        NetworkStatus.PoorWifi
                    }
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)) {
                        NetworkStatus.GoodMobile
                    } else {
                        NetworkStatus.PoorMobile
                    }
                } else {
                    NetworkStatus.Unknown
                }
            }
            else -> NetworkStatus.Disconnected
        }
    }

    sealed class NetworkStatus {
        object GoodWifi : NetworkStatus()
        object PoorWifi : NetworkStatus()
        object GoodMobile : NetworkStatus()
        object PoorMobile : NetworkStatus()
        object Disconnected : NetworkStatus()
        object Unknown : NetworkStatus()
    }

}