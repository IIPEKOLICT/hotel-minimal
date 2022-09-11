package hotel.minimal.client.repositories

import hotel.minimal.client.data.dtos.HealthCheckDto
import retrofit2.Response
import retrofit2.http.*

interface MainRepository {
    
    @GET("/")
    suspend fun healthCheck(): Response<HealthCheckDto>
}