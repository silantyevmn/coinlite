package silantyevmn.ru.coinlite.ui.activity

import android.os.Bundle
import android.text.format.Formatter
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import silantyevmn.ru.coinlite.App
import silantyevmn.ru.coinlite.R
import silantyevmn.ru.coinlite.mvp.presenter.StartPresenter
import silantyevmn.ru.coinlite.mvp.view.StartView
import silantyevmn.ru.coinlite.ui.BackButtonListener
import java.util.*
import javax.inject.Inject

class StartActivity : MvpAppCompatActivity(), StartView {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = AppNavigator(this, R.id.main_container)

    private val presenter by moxyPresenter {
        StartPresenter(router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.getComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.toMainScreen()

//        nav_view.setOnNavigationItemSelectedListener {
//            presenter.toMainScreen()
//            return@setOnNavigationItemSelectedListener true;
//        }
    }

    override fun init() {
        //
    }

    override fun onBackPressed() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.main_container)
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            super.onBackPressed()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
