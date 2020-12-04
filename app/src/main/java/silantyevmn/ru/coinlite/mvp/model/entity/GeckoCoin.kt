package silantyevmn.ru.coinlite.mvp.model.entity

import java.io.Serializable

data class GeckoCoin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Float,
    val marketCap: String,
    val marketCapRank: Int,
    val totalVolume: Float,
    val priceChangePercentage24h: Float,
    val marketCapChangePercentage24h: Float,
    val circulatingSupply: Double,
    val totalSupply: Float,
    val ath: Float,
    val athChangePercentage: Float
): Serializable