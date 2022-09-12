package hotel.minimal.client.domain.useCases

import hotel.minimal.client.domain.interfaces.ITestRepository

class HealthCheckUseCase(private val repository: ITestRepository) {

    suspend fun healthCheck(): Boolean {
        return repository.healthCheck()
    }
}