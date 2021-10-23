package castelles.com.github.androidbaseproject.module

import castelles.com.github.androidbaseproject.viewmodel.UserViewModel
import castelles.com.github.api.datasource.UserDataSource
import castelles.com.github.api.repository.UserRepositoryImpl
import castelles.com.github.api.repository.contract.UserRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModules = module {
    single { UserDataSource() }
}

val repositoryModules = module {
    single { UserRepositoryImpl(get()) } bind UserRepository::class
}

val viewModelModules = module {
    viewModel { UserViewModel(get()) }
}