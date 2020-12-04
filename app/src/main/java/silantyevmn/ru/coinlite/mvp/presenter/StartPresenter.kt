package silantyevmn.ru.coinlite.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import silantyevmn.ru.coinlite.ui.Screens.HomeScreen
import silantyevmn.ru.coinlite.ui.Screens.StartScreen
import silantyevmn.ru.coinlite.mvp.view.StartView
import silantyevmn.ru.coinlite.ui.BackButtonListener

@InjectViewState
class StartPresenter(val router: Router): MvpPresenter<StartView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState?.init()
    }

    fun onBackPressed() {
        router.exit()
    }

    fun restartActivity() {
        router.replaceScreen(StartScreen())
    }

    fun toMainScreen() {
        router.replaceScreen(HomeScreen())
    }
}