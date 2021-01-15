package silantyevmn.ru.coinlite.mvp.model.cache.paper

import io.paperdb.Paper
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import silantyevmn.ru.coinlite.mvp.model.cache.Cache
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest
import java.lang.Exception
import java.lang.RuntimeException

class PaperCache : Cache {
    private val BASE_KEY = "CoinMarket"
    private val KEY_CHART = "Chart"
    override fun getCoinMarket(): Observable<List<GeckoCoinRest>> {
        return Observable.create { emitter: ObservableEmitter<List<GeckoCoinRest>> ->
            try {
                val list: List<GeckoCoinRest> = Paper.book().read(BASE_KEY);
                emitter.onNext(list)
            } catch (e: Exception) {
                emitter.onError(RuntimeException("Cache CoinMarket is null"))
            }
            emitter.onComplete()
        }
    }

    override fun putAllCoinMarket(list: List<GeckoCoinRest>): Boolean {
        try {
            if (Paper.book().contains(BASE_KEY)) {
                Paper.book().delete(BASE_KEY)
            }
            Paper.book().write(BASE_KEY, list)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun getChartById(id: String): Observable<GeckoCoinChartRest> {
        return Observable.create { emitter: ObservableEmitter<GeckoCoinChartRest> ->
            try {
                val item: GeckoCoinChartRest = Paper.book(KEY_CHART).read(id)
                emitter.onNext(item)
            } catch (e: Exception) {
                //нет базы
                emitter.onError(RuntimeException("Cache Chart is null"))
            }
            emitter.onComplete()
        }
    }

    override fun putChartById(id: String, chart: GeckoCoinChartRest): Boolean {
        try {
            if (Paper.book(KEY_CHART).contains(id)) {
                Paper.book(KEY_CHART).delete(id)
            }
            Paper.book(KEY_CHART).write(id, chart)
            return true
        } catch (e: Exception) {
            return false
        }
    }


}