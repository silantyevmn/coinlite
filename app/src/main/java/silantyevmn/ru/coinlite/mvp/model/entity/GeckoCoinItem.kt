package silantyevmn.ru.coinlite.mvp.model.entity

import java.io.Serializable

data class GeckoCoinItem(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: String,
    val marketCap: String,
    val marketCapRank: String,
    val totalVolume: String,
    val priceChangePercentage24h: String,
    val marketCapChangePercentage24h: String,
    val circulatingSupply: String,
    val totalSupply: String,
    val ath: String,
    val athChangePercentage: String
): Serializable