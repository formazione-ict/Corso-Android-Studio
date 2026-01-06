package com.example.broadcastdemo

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class NetworkStatusManager(
    private val context: Context,
    private val onNetworkStatusChanged: (String) -> Unit
) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    fun register() {
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                onNetworkStatusChanged("Connesso a Internet")
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                onNetworkStatusChanged("Non connesso a Internet")
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        networkCallback?.let {
            connectivityManager.registerNetworkCallback(networkRequest, it)
        }
        
        // Check initial state
        val activeNetwork = connectivityManager.activeNetwork
        if (activeNetwork == null) {
            onNetworkStatusChanged("Non connesso a Internet")
        }
    }

    fun unregister() {
        networkCallback?.let {
            connectivityManager.unregisterNetworkCallback(it)
        }
    }
}
