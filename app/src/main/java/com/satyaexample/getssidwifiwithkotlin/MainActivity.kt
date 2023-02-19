package com.satyaexample.getssidwifiwithkotlin

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val PERMISSIONS_REQUEST_CODE = 123


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "KotlinApp"
        val textView: TextView = findViewById(R.id.ssidtxt)
        if (checkLocationPermission(applicationContext)) {
            val ssid = getWifiSSID(applicationContext)
            // Do something with the SSID
            textView.text = "Wifi SSID: $ssid"
        } else {
            requestLocationPermission(this)
        }

    }

    fun getWifiSSID(context: Context): String {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        if (network != null) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return wifiInfo.ssid
            }
        }
        return ""
    }

    fun checkLocationPermission(context: Context): Boolean {
        val permission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        return permission == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermission(activity: MainActivity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSIONS_REQUEST_CODE
        )
    }

}