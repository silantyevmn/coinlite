package silantyevmn.ru.coinlite.mvp.model.repo

import io.reactivex.Observable
import silantyevmn.ru.coinlite.mvp.model.api.GeckoApi
import silantyevmn.ru.coinlite.mvp.model.cache.Cache
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest
import silantyevmn.ru.coinlite.utils.NetworkStatus

class GeckoRepo(val api: GeckoApi, val cache: Cache) {

    fun getCoinMarket(): Observable<List<GeckoCoinRest>> {
        return if (NetworkStatus.isOnline) {
            api.getCoinMarket()
                .doOnNext { list ->
                    cache.putAllCoinMarket(list)
                }
        } else {
            cache.getCoinMarket()
        }
    }

    fun getChartCoin(id: String): Observable<GeckoCoinChartRest> {
        return if (NetworkStatus.isOnline) {
            api.getChartById(id)
                .map {chart ->
                    chart.id = id
                    return@map chart
                }
                .doOnNext { chart ->
                    cache.putChart(chart)
                }
        } else {
            cache.getChartById(id)
        }
    }
}