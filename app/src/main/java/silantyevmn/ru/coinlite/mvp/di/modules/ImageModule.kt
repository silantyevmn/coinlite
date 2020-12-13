package silantyevmn.ru.coinlite.mvp.di.modules

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import silantyevmn.ru.coinlite.mvp.model.image.ImageLoader
import silantyevmn.ru.coinlite.mvp.model.image.ImageLoaderGlade
import javax.inject.Singleton

@Module
class ImageModule {
    @Provides
    @Singleton
    fun imageLoader():ImageLoader<ImageView> {
        return ImageLoaderGlade();
    }
}