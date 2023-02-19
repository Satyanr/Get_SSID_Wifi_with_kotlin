# Get_SSID_Wifi_with_kotlin
example

The importan file is in kotlin file and permission in android manifest

if you want to get bssid just change return wifiInfo.ssid to return wifiInfo.bssid

fun getWifiBSSID(context: Context): String {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        if (network != null) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return wifiInfo.bssid
            }
        }
        return ""
    }
    
    in MainActivity.kt
