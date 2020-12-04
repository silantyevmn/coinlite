package silantyevmn.ru.coinlite.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import silantyevmn.ru.coinlite.mvp.model.entity.*

@StateStrategyType(AddToEndSingleStrategy::class)
interface ChartView : MvpView,BaseView,LoadingView {
    fun addEntryToChart(value: Float, date: String = "")
    fun addEntryToChart(date: Float, value: Float)
    fun showErrorMessage(error: String?)
    fun refresh(coinItem: Any)

    fun showToast(msg: String)
    fun updateChart(chartItem: List<List<Float>>)
}