package ecnill.country.app

import android.app.Application
import ecnill.country.app.di.AppGraph
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

internal class CountriesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CountriesApp)
            modules(AppGraph.modules)
        }

        Timber.plant(Timber.DebugTree())
    }
}