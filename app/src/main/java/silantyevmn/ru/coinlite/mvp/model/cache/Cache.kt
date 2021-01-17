package silantyevmn.ru.coinlite.mvp.model.cache

import io.reactivex.Observable
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest

interface Cache{
    fun getCoinMarket(): Observable<List<GeckoCoinRest>>
    fun putAllCoinMarket(list:List<GeckoCoinRest>): Boolean

    fun getChartById(id: String): Observable<GeckoCoinChartRest>
    fun putChart(chart:GeckoCoinChartRest): Boolean


}