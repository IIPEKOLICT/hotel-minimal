package hotel.minimal.client.data.repositories.impl

import hotel.minimal.client.data.repositories.TestRepository
import hotel.minimal.client.domain.interfaces.ITestRepository

class TestRepositoryImpl(
    private val repository: TestRepository
) : BaseRepositoryImpl(), ITestRepository {

    override suspend fun healthCheck(): Boolean {
        return parseResponse(repository.healthCheck()).status == "ok"
    }
}