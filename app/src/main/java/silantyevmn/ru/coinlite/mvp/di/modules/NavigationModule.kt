package silantyevmn.ru.coinlite.mvp.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    private val cicerone: Cicerone<Router>
    @Provides
    @Singleton
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Provides
    @Singleton
    fun provideNavigationHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

    init {
        cicerone = Cicerone.create()
    }
}