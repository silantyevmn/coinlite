package silantyevmn.ru.coinlite.mvp.model.cache.room


import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import silantyevmn.ru.coinlite.mvp.model.cache.Cache
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest
import java.lang.Exception
import java.lang.RuntimeException

class CoinRoom(val dataBase: CoinRoomAbs) : Cache {

    override fun getCoinMarket(): Observable<List<GeckoCoinRest>> {
        return Observable.create { emitter: ObservableEmitter<List<GeckoCoinRest>> ->
            try {
                val coinMarkets = dataBase.photoDao().getCoinMarket()
                if (coinMarkets.isEmpty()) {
                    throw RuntimeException("Cache CoinMarkets is null")
                }
                emitter.onNext(coinMarkets)
            } catch (e: Exception) {
                emitter.onError(e)
            }
            emitter.onComplete()
        }
    }

    override fun putAllCoinMarket(list: List<GeckoCoinRest>): Boolean {
        dataBase.photoDao().putAllCoinMarket(list)
        return true
    }

    override fun getChartById(id: String): Observable<GeckoCoinChartRest> {
        return Observable.create { emitter: ObservableEmitter<GeckoCoinChartRest> ->
            try {
                val chart = dataBase.photoDao().getChart(id)
                if (chart == null) {
                    throw RuntimeException("Cache Chart is null")
                }
                emitter.onNext(chart)
            } catch (e: Exception) {
                emitter.onError(e)
            }
            emitter.onComplete()
        }
    }

    override fun putChart(chart: GeckoCoinChartRest): Boolean {
        dataBase.photoDao().putChart(chart)
        return true
    }
}