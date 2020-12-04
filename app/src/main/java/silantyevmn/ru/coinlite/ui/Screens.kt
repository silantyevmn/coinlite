package silantyevmn.ru.coinlite.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import silantyevmn.ru.coinlite.mvp.model.entity.GeckoCoin
import silantyevmn.ru.coinlite.ui.fragment.*

object Screens {
    fun StartScreen() = FragmentScreen { HomeFragment() }
    fun HomeScreen() = FragmentScreen { HomeFragment() }
//    fun DetailsScreen(id: String) = FragmentScreen("key_${id}") { DetailsFragment(id.toString()) }
    fun DetailsScreen(coin: GeckoCoin) = FragmentScreen {ChartFragment.getNewInstance(coin) }
}