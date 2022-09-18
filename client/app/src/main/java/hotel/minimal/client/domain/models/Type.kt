package hotel.minimal.client.domain.models

import com.google.gson.annotations.SerializedName
import hotel.minimal.client.data.interfaces.IEntity

data class Type(
    @SerializedName("id") override val id: Int = 0,
    @SerializedName("rooms") val rooms: List<Int> = emptyList(),
    @SerializedName("name") var name: String = "",
    @SerializedName("options") var options: String = "no options",
    @SerializedName("price") var price: Int = 0
) : IEntity
