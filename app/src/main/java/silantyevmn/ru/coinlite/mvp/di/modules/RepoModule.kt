package silantyevmn.ru.coinlite.mvp.di.modules

import dagger.Module
import dagger.Provides
import silantyevmn.ru.coinlite.mvp.model.api.GeckoApi
import silantyevmn.ru.coinlite.mvp.model.repo.GeckoRepo

@Module(includes = [ApiModule::class, CacheModule::class,AppModule::class])
class RepoModule {
    @Provides
    fun provideGeckoRepo(api: GeckoApi): GeckoRepo {
        return GeckoRepo(api)
    }
}