package silantyevmn.ru.coinlite.mvp.model.entity.maper

import silantyevmn.ru.coinlite.mvp.model.entity.GeckoCoin
import silantyevmn.ru.coinlite.mvp.model.entity.GeckoCoinItem
import silantyevmn.ru.coinlite.mvp.model.entity.rest.GeckoCoinRest
import silantyevmn.ru.coinlite.utils.GeckoCoinManager
import java.lang.String

object GeckoCoinMapper {
    fun mapCoin(list: List<GeckoCoinRest>): List<GeckoCoin> {
        return list.map {
            GeckoCoin(
                id=it.id,
                symbol = it.symbol,
                name = it.name,
                image = it.image,
                currentPrice = it.currentPrice,
                marketCap = GeckoCoinManager.coinFormatThousands(it.marketCap),
                marketCapRank = it.marketCapRank,
                totalVolume = it.totalVolume,
                priceChangePercentage24h=it.priceChangePercentage24h,
                marketCapChangePercentage24h=it.marketCapChangePercentage24h,
                circulatingSupply=it.circulatingSupply,
                totalSupply=it.totalSupply,
                ath=it.ath,
                athChangePercentage=it.athChangePercentage
            )
        }
    }

    fun mapCoinDetails(coin: GeckoCoin): GeckoCoinItem {
        return GeckoCoinItem(
                id=coin.id,
                symbol = coin.symbol,
                name = coin.name,
                image = coin.image,
                currentPrice = coin.currentPrice.toString(),
                marketCap = coin.marketCapRank.toString(),
                marketCapRank = coin.marketCapRank.toString(),
                totalVolume = coin.totalVolume.toString(),
                priceChangePercentage24h=coin.priceChangePercentage24h.toString(),
                marketCapChangePercentage24h= String.format("%.1f", coin.athChangePercentage),
                circulatingSupply=String.format("%,.0f", coin.circulatingSupply),
                totalSupply=String.format("%,.0f", coin.totalSupply),
                ath=String.format("%,.0f", coin.ath),
                athChangePercentage=String.format("%.1f", coin.athChangePercentage)
            )
        }

}