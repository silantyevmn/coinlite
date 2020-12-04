package silantyevmn.ru.coinlite

import android.app.Application
import io.paperdb.Paper
import silantyevmn.ru.coinlite.mvp.di.AppComponent
import silantyevmn.ru.coinlite.mvp.di.DaggerAppComponent
import silantyevmn.ru.coinlite.mvp.di.modules.AppModule
import timber.log.Timber
import timber.log.Timber.DebugTree

class App: Application() {
    companion object{
        private var instance: App? = null
        private var component: AppComponent? = null

        fun getInstance(): App? {
            return instance
        }

        fun getComponent(): AppComponent? {
            if (component == null) {
                component = DaggerAppComponent.builder()
                    .appModule(AppModule(this.instance!!))
                    .build()
            }
            return component
        }

    }



    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(DebugTree())
        Paper.init(this)
//        Stetho.initializeWithDefaults(this)
    }
}