package silantyevmn.ru.coinlite.mvp.model.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest
import silantyevmn.ru.coinlite.utils.Converters

@Database(
    entities = [GeckoCoinRest::class,GeckoCoinChartRest::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CoinRoomAbs : RoomDatabase() {
    abstract fun photoDao(): CoinRoomDao
}