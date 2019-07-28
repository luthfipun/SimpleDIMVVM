package studio.elevenbox.simple_di_mvvm

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import studio.elevenbox.simple_di_mvvm.data.network.ApiService
import studio.elevenbox.simple_di_mvvm.data.network.NetworkDataSource
import studio.elevenbox.simple_di_mvvm.data.network.NetworkDataSourceImpl
import studio.elevenbox.simple_di_mvvm.data.repository.UserRepository
import studio.elevenbox.simple_di_mvvm.data.repository.UserRepositoryImpl
import studio.elevenbox.simple_di_mvvm.ui.main.MainViewModelFactory

class App : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { ApiService() }
        bind<NetworkDataSource>() with singleton { NetworkDataSourceImpl(instance()) }
        bind<UserRepository>() with singleton { UserRepositoryImpl(instance()) }
        bind() from provider { MainViewModelFactory(instance()) }
    }
}