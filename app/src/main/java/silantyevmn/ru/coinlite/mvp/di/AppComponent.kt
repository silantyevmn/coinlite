package silantyevmn.ru.coinlite.mvp.di

import dagger.Component
import silantyevmn.ru.coinlite.mvp.di.modules.*
import silantyevmn.ru.coinlite.ui.chart.LatestChart
import silantyevmn.ru.coinlite.ui.activity.StartActivity
import silantyevmn.ru.coinlite.ui.fragment.*
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class, RepoModule::class, ImageModule::class,AppModule::class,ChartModule::class])
interface AppComponent {
    fun inject(startActivity: StartActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(detailsFragment: ChartFragment)
    fun inject(latestChart: LatestChart)
}