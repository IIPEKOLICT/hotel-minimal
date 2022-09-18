package hotel.minimal.client.presentation.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hotel.minimal.client.domain.useCases.HealthCheckUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConnectionViewModel @Inject constructor(
    override val app: Application,
    private val healthCheckUseCase: HealthCheckUseCase
) : BaseViewModel(app) {

    val hasConnection: LiveData<Boolean>
        get() = healthCheckUseCase.hasConnection

    fun checkConnection() {
        viewModelScope.launch(Dispatchers.IO) {
            healthCheckUseCase.healthCheck()
        }
    }
}