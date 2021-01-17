package silantyevmn.ru.coinlite.mvp.model.cache.room


import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.SingleEmitter
import silantyevmn.ru.coinlite.mvp.model.cache.Cache
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest

class CoinRoom(val dataBase: CoinRoomAbs) : Cache {

    override fun getCoinMarket(): Single<List<GeckoCoinRest>> {
        return Single.create { emitter: SingleEmitter<List<GeckoCoinRest>> ->
            try {
                val coinMarkets = dataBase.photoDao().getCoinMarket()
                if(coinMarkets.isEmpty()) throw RuntimeException("Cache CoinMarkets is null")
                emitter.onSuccess(coinMarkets)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun putAllCoinMarket(list: List<GeckoCoinRest>): Boolean {
        try {
            dataBase.photoDao().putAllCoinMarket(list)
            return true
        } catch (e: Exception) {
            return false
        }

    }

    override fun getChartById(id: String): Observable<GeckoCoinChartRest> {
        return Observable.create { emitter: ObservableEmitter<GeckoCoinChartRest> ->
            try {
                val chart = dataBase.photoDao().getChart(id)
                    ?: throw RuntimeException("Cache Chart is null")
                emitter.onNext(chart)
            } catch (e: Exception) {
                emitter.onError(e)
            }
            emitter.onComplete()
        }
    }

    override fun putChart(chart: GeckoCoinChartRest): Boolean {
        try {
            dataBase.photoDao().putChart(chart)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}