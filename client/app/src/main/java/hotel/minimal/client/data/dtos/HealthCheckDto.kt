package hotel.minimal.client.data.dtos

import com.google.gson.annotations.SerializedName

data class HealthCheckDto(@SerializedName("status") val status: String = "ok")
