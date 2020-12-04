package silantyevmn.ru.coinlite.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LoadingView:MvpView {
    fun showLoading()
    fun hideLoading()
}