package silantyevmn.ru.coinlite.mvp.model.repo

import io.reactivex.Observable
import silantyevmn.ru.coinlite.mvp.model.api.GeckoApi
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest

class GeckoRepo(val api: GeckoApi) {

    fun getCoinMarket(): Observable<List<GeckoCoinRest>> {
        return api.getCoinMarket()
    }

    fun getCoinMarket(id: String): Observable<GeckoCoinChartRest> {
        return api.getCoinMarketChart(id)
    }
}