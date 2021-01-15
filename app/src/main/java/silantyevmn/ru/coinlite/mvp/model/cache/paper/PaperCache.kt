package silantyevmn.ru.coinlite.mvp.model.cache.paper

import io.paperdb.Paper
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import silantyevmn.ru.coinlite.mvp.model.cache.Cache
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinChartRest
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest
import java.lang.Exception
import java.util.*

class PaperCache(): Cache{
    val BASE_KEY = "CoinMarket"
    val KEY_CHART = "Chart"
    override fun getCoinMarket(): Observable<List<GeckoCoinRest>> {
        return Observable.create{ emitter: ObservableEmitter<List<GeckoCoinRest>> ->
            var list : List<GeckoCoinRest> = emptyList()
            try {
                list = Paper.book().read(BASE_KEY);
            } catch (e:Exception){
                //нет базы вернем emptyList
            }
            emitter.onNext(list)
            emitter.onComplete()
        }
    }

    override fun putAllCoinMarket(list: List<GeckoCoinRest>): Boolean {
        try {
            Paper.book().delete(BASE_KEY)
            Paper.book().write(BASE_KEY, list)
            return true
        } catch (e: Exception){
            return false
        }
    }

    override fun getChartById(id: String): Observable<GeckoCoinChartRest> {
        return Observable.create { emitter: ObservableEmitter<GeckoCoinChartRest> ->
            try {
                val item: GeckoCoinChartRest = Paper.book(KEY_CHART).read(id)
                emitter.onNext(item);
            } catch (e: Exception) {
                //нет базы вернем пустой объект
                emitter.onNext(GeckoCoinChartRest(listOf()))
            }
            emitter.onComplete()
        }
    }

    override fun putChartById(id: String, chart: GeckoCoinChartRest): Boolean{
        try {
            Paper.book(KEY_CHART).delete(id)
            Paper.book(KEY_CHART).write(id,chart)
            return true
        } catch (e: Exception){
            return false
        }
    }


}