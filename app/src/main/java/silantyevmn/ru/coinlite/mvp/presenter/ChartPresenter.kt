package silantyevmn.ru.coinlite.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import silantyevmn.ru.coinlite.mvp.model.entity.*
import silantyevmn.ru.coinlite.mvp.model.entity.maper.GeckoCoinMapper
import silantyevmn.ru.coinlite.mvp.model.repo.GeckoRepo
import silantyevmn.ru.coinlite.mvp.view.ChartView
import java.lang.Exception

class ChartPresenter(
    private val mainThreadScheduler: Scheduler,
    private val router: Router,
    private val repo: GeckoRepo,
    private val coin: GeckoCoin
) : MvpPresenter<ChartView>() {
    val compositeDisposable = CompositeDisposable()

    fun loadData() {
        //отобразим coin
        compositeDisposable.add(
            Observable.just(coin)
                .map { coin ->
                    GeckoCoinMapper.mapCoinDetails(coin)
                }
                .subscribe { coinItem ->
                    viewState.refresh(coinItem)
                }
        )

        compositeDisposable.add(
            repo.getChartCoin(coin.id)
                .subscribeOn(Schedulers.io())
                .map {chart -> chart.prices }
                .flatMap { chartPrices -> Observable.fromIterable(chartPrices) }
//                .take(600)
                .filter{
                    !it.isEmpty()
                }
//                .doOnNext { chartItem -> viewState.addEntryToChart(chartItem[0], chartItem[1]) }
                .observeOn(mainThreadScheduler)
                .doOnNext { chartItem -> viewState.addEntryToChart(chartItem[0], chartItem[1]) }
                .doOnSubscribe { viewState.showLoading() }
                .doOnError { viewState.hideLoading() }
                .doOnComplete { viewState.hideLoading() }
                .subscribe({ chartItem ->
                    viewState.hideLoading()
//                    viewState.addEntryToChart(chartItem[0], chartItem[1])
                    viewState.updateChart(listOf())
//                    viewState.addEntryToChart(chartItem[0], chartItem[1])
                }, { error ->
                    viewState.hideLoading()
                    viewState.showToast(error.message!!)
                    error.printStackTrace()
                })
        )
    }

    fun onStop() {
        viewState.hideLoading()
        compositeDisposable.clear()
    }

    fun onBackPressed() {
        onStop()
        router.exit()
    }
}



