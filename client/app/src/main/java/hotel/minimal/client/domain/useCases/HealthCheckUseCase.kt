package hotel.minimal.client.domain.useCases

import androidx.lifecycle.LiveData
import hotel.minimal.client.domain.interfaces.ITestService
import javax.inject.Inject

class HealthCheckUseCase @Inject constructor(
    private val testService: ITestService
) {

    val hasConnection: LiveData<Boolean>
        get() = testService.liveData

    suspend fun healthCheck() {
        testService.healthCheck()
    }
}