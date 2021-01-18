package silantyevmn.ru.coinlite.mvp.model.cache

import io.reactivex.Observable
import io.reactivex.Single
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest

interface Cache{
    fun getCoinMarket(): Single<List<GeckoCoinRest>>
    fun putAllCoinMarket(list:List<GeckoCoinRest>): Boolean

    fun getChartById(id: String): Observable<GeckoCoinChartRest>
    fun putChart(chart:GeckoCoinChartRest): Boolean


}