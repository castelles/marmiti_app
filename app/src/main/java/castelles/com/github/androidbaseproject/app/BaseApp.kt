package castelles.com.github.androidbaseproject.app

import android.app.Application
import castelles.com.github.androidbaseproject.module.dataSourceModules
import castelles.com.github.androidbaseproject.module.repositoryModules
import castelles.com.github.androidbaseproject.module.viewModelModules
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