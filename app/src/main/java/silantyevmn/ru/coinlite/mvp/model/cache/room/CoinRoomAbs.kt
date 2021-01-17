package silantyevmn.ru.coinlite.mvp.model.cache.room

import androidx.room.Database
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest

@Database(
    entities = [GeckoCoinRest::class, GeckoCoinChartRest::class],
    version = 1,
    exportSchema = false
)
abstract class CoinRoomAbs : RoomDatabase() {
    abstract fun photoDao(): CoinRoomDao?
}