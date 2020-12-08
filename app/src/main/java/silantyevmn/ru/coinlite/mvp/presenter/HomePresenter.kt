package silantyevmn.ru.coinlite.mvp.presenter

import android.annotation.SuppressLint
import com.github.terrakok.cicerone.Router
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import silantyevmn.ru.coinlite.mvp.model.entity.GeckoCoin
import silantyevmn.ru.coinlite.mvp.model.entity.maper.GeckoCoinMapper
import silantyevmn.ru.coinlite.mvp.model.repo.GeckoRepo
import silantyevmn.ru.coinlite.mvp.view.HomeView
import silantyevmn.ru.coinlite.ui.Screens

class HomePresenter(
    private val mainThreadScheduler: Scheduler,
    private val router: Router,
    private val repo: GeckoRepo
) : MvpPresenter<HomeView>() {

    val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    @SuppressLint("CheckResult")

    fun loadData() {
        compositeDisposable.add(
            repo.getCoinMarket()
                .subscribeOn(Schedulers.io())
                .map {
                    return@map GeckoCoinMapper.mapCoin(it)
                }
                .observeOn(mainThreadScheduler)
                .doOnSubscribe { viewState.showLoading() }
                .doOnError { viewState.hideLoading() }
                .doOnComplete { viewState.hideLoading() }
                .subscribe({
                    if (viewState != null) {
                        viewState.updateList(it)
                    }
                }, {
                    viewState.showError(it.message!!)
                })
        )
    }

    fun onItemClickById(coin: GeckoCoin) {
        router.navigateTo(Screens.DetailsScreen(coin))
    }

    fun onStop() {
        viewState.hideLoading()
        compositeDisposable.clear()
    }
}



