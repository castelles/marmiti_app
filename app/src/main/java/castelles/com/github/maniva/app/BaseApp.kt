package castelles.com.github.maniva.app

import android.app.Application
import castelles.com.github.maniva.module.dataSourceModules
import castelles.com.github.maniva.module.repositoryModules
import castelles.com.github.maniva.module.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApp)
            modules(dataSourceModules, repositoryModules, viewModelModules)
        }
    }
}