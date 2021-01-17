package silantyevmn.ru.coinlite.mvp.model.cache.room

import androidx.room.*
import io.reactivex.Observable
import silantyevmn.ru.coinlite.mvp.model.entity.GeckoCoinItem
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest

/*
fun getCoinMarket(): Observable<List<GeckoCoinRest>>
    fun putAllCoinMarket(list:List<GeckoCoinRest>): Boolean

    fun getChartById(id: String): Observable<GeckoCoinChartRest>
    fun putChartById(id: String,chart:GeckoCoinChartRest): Boolean

 */

@Dao
interface CoinRoomDao{
    //coins
    @Query("SELECT * FROM coins")
    fun getCoinMarket(): List<GeckoCoinRest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putAllCoinMarket(list:List<GeckoCoinRest>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCoins(list:List<GeckoCoinRest>)

    @Delete
    fun deleteCoins(list:List<GeckoCoinRest>)

    //chart
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putChart(chart: GeckoCoinChartRest)

    @Query("SELECT * FROM charts WHERE id =:charId")
    fun getChart(charId: String): GeckoCoinChartRest?

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateChart(chart: GeckoCoinChartRest)

    @Delete
    fun deleteChart(chart: GeckoCoinChartRest)
}