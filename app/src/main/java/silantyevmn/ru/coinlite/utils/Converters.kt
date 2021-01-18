package silantyevmn.ru.coinlite.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromPrices(value: String): List<List<Float>> {
        return Gson().fromJson<List<List<Float>>>(value, List::class.java)
    }

    @TypeConverter
    fun pricesToJson(list: List<List<Float>>): String {
        return Gson().toJson(list)
    }
}