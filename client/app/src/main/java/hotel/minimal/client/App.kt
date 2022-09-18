package hotel.minimal.client

import android.app.Application
import hotel.minimal.client.data.DataSource
import hotel.minimal.client.di.components.AppComponent
import hotel.minimal.client.di.components.DaggerAppComponent
import hotel.minimal.client.di.modules.ApplicationModule
import hotel.minimal.client.di.modules.RepositoryModule

class App : Application() {
    val rootInjector: AppComponent = DaggerAppComponent
        .builder()
        .repositoryModule(RepositoryModule(DataSource))
        .applicationModule(ApplicationModule(this))
        .build()
}