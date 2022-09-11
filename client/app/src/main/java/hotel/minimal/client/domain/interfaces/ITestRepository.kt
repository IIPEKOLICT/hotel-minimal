package hotel.minimal.client.domain.interfaces

interface ITestRepository {
    suspend fun healthCheck(): Boolean
}