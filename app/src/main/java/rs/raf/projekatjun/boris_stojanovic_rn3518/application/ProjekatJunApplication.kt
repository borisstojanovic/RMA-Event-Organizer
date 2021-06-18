package rs.raf.projekatjun.boris_stojanovic_rn3518.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.projekatjun.boris_stojanovic_rn3518.modules.coreModule
import rs.raf.projekatjun.boris_stojanovic_rn3518.modules.eventModule
import rs.raf.projekatjun.boris_stojanovic_rn3518.modules.locationModule
import timber.log.Timber

class ProjekatJunApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            locationModule,
            eventModule,
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@ProjekatJunApplication)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }

}