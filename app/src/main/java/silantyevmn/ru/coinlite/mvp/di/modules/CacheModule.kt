package silantyevmn.ru.coinlite.mvp.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import silantyevmn.ru.coinlite.mvp.model.cache.Cache
import silantyevmn.ru.coinlite.mvp.model.cache.room.CoinRoom
import silantyevmn.ru.coinlite.mvp.model.cache.room.CoinRoomAbs
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class CacheModule {
    @Provides
    @Singleton
    fun provideCacheRoom(database: CoinRoomAbs): Cache {
        return CoinRoom(database)
    }

    @Named("baseName")
    @Provides
    fun provideBaseName(): String {
        return "coinbaseDB"
    }

    @Provides
    @Singleton
    fun provideDataBaseRoom(@Named("baseName")baseName: String, context: Context): CoinRoomAbs {
        return Room.databaseBuilder(context, CoinRoomAbs::class.java, baseName)
            .build()
    }


}