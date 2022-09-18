package hotel.minimal.client.presentation.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hotel.minimal.client.data.DataSource
import hotel.minimal.client.domain.useCases.HealthCheckUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConnectionViewModel(override val app: Application) : BaseViewModel(app) {

    private val healthCheckUseCase = HealthCheckUseCase(DataSource.testService)

    val hasConnection: LiveData<Boolean>
        get() = healthCheckUseCase.hasConnection

    fun checkConnection() {
        viewModelScope.launch(Dispatchers.IO) {
            healthCheckUseCase.healthCheck()
        }
    }
}