package silantyevmn.ru.coinlite.utils

import java.text.SimpleDateFormat
import java.util.*

object GeckoCoinManager {
    fun coinFormatThousands(value: Float) : String {
        val sb = StringBuilder()
        try {
            val formatter = Formatter(sb, Locale.US)
            formatter.format("%(,.0f", value)
            return sb.toString()
        }
        catch (e:Exception) {
           return ""
        }
    }

    fun convertDateLongToString(long: Long): String {
        try {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = long
            return SimpleDateFormat("MMM dd, yyyy").format(calendar.time)
        }
        catch (e:Exception) {
            return ""
        }

    }

    fun coinFormatDouble(value: Float) : String {
        val sb = StringBuilder()
        try {
            val formatter = Formatter(sb, Locale.US)
            formatter.format("%.2f", value)
            return sb.toString()
        }
        catch (e:Exception) {
            return ""
        }
    }

}