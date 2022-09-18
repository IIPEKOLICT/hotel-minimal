package hotel.minimal.client.domain.models

import com.google.gson.annotations.SerializedName
import hotel.minimal.client.data.interfaces.IEntity

data class Comment(
    @SerializedName("id") override val id: Int = 0,
    @SerializedName("room") val room: Int = 0,
    @SerializedName("content") var content: String = ""
) : IEntity
