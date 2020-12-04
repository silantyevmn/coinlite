package silantyevmn.ru.coinlite.utils

import android.content.Context
import android.net.ConnectivityManager
import android.provider.Settings
import silantyevmn.ru.coinlite.App.Companion.getInstance

object NetworkStatus {
    private val status: Status
        private get() {
            val currentStatus = Status.OFFLINE
            val cm =
                getInstance()!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return if (activeNetwork != null) {
                when (activeNetwork.type) {
                    ConnectivityManager.TYPE_WIFI -> Status.WIFI
                    ConnectivityManager.TYPE_ETHERNET -> Status.ETHERNET
                    ConnectivityManager.TYPE_MOBILE -> Status.MOBILE
                    else -> Status.OFFLINE
                }
            } else currentStatus
        }
    val isOnline: Boolean
        get() {
            val currentStatus = status
            return currentStatus != Status.OFFLINE
        }
    private val isAirplane: Boolean
        private get() = Settings.Global.getInt(
            getInstance()!!.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0
    val isWifi: Boolean
        get() = status == Status.WIFI
    val isEthernet: Boolean
        get() = status == Status.ETHERNET
    val isMobile: Boolean
        get() = status == Status.MOBILE
    val isOffline: Boolean
        get() = status == Status.OFFLINE

    enum class Status {
        WIFI, MOBILE, ETHERNET, OFFLINE
    }
}