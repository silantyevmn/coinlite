package silantyevmn.ru.coinlite.mvp.di.modules

import dagger.Module
import dagger.Provides
import silantyevmn.ru.coinlite.mvp.model.cache.Cache
import silantyevmn.ru.coinlite.mvp.model.cache.paper.PaperCache
import silantyevmn.ru.coinlite.utils.YearValueFormatter
import javax.inject.Singleton

@Module
class ChartModule {

    @Provides
    @Singleton
    fun formatted(): YearValueFormatter {
        return YearValueFormatter()
    }
}