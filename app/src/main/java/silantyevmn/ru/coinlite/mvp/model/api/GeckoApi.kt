package silantyevmn.ru.coinlite.mvp.model.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import silantyevmn.ru.coinlite.mvp.model.entity.rest.*

interface GeckoApi {
    ////https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=10&page=1&sparkline=false
    //запрос списка криптовалют
    @GET("coins/markets")
    fun getCoinMarket(
        @Query("vs_currency") vs: String = "usd",
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("order") order: String = "market_cap_desc"
    ): Observable<List<GeckoCoinRest>>

    //запрос данных для графика
    @GET("coins/{id}/market_chart")
    fun getCoinMarketChart(
        @Path("id") id: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: String = "90"
    ): Observable<GeckoCoinChartRest>
}