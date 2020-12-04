package silantyevmn.ru.coinlite.mvp.di.modules

import dagger.Module
import dagger.Provides
import silantyevmn.ru.coinlite.mvp.model.cache.Cache
import silantyevmn.ru.coinlite.mvp.model.cache.paper.PaperCache
import javax.inject.Singleton

@Module
class CacheModule {
    @Provides
    @Singleton
    fun cachePaper(): Cache {
        return PaperCache()
    }
}