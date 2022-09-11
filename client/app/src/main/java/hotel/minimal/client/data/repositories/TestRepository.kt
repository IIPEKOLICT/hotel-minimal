package hotel.minimal.client.data.repositories

import hotel.minimal.client.data.dtos.HealthCheckDto
import retrofit2.Response
import retrofit2.http.*

interface TestRepository {
    
    @GET("/test")
    suspend fun healthCheck(): Response<HealthCheckDto>
}