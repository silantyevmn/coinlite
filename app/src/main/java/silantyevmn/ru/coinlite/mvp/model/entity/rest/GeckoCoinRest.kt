package silantyevmn.ru.coinlite.mvp.model.entity.rest

import com.google.gson.annotations.SerializedName

data class GeckoCoinRest(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price")
    val currentPrice: Float,

    @SerializedName("market_cap")
    val marketCap: Float,

    @SerializedName("market_cap_rank")
    val marketCapRank: Int,

    @SerializedName("total_volume")
    val totalVolume: Float,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Float,

    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Float,

    @SerializedName("circulating_supply")
    val circulatingSupply: Double,

    @SerializedName("total_supply")
    val totalSupply: Float,
    val ath: Float,

    @SerializedName("ath_change_percentage")
    val athChangePercentage: Float
)