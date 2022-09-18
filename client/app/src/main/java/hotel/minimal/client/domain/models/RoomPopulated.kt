package hotel.minimal.client.domain.models

import com.google.gson.annotations.SerializedName
import hotel.minimal.client.data.interfaces.IEntity

data class RoomPopulated(
    @SerializedName("id") override val id: Int = 0,
    @SerializedName("type") val type: Type,
    @SerializedName("comments") var comments: MutableSet<Comment> = mutableSetOf(),
    @SerializedName("description") var description: String = "",
    @SerializedName("address") var address: String = "",
    @SerializedName("floor") var floor: Int = 0,
    @SerializedName("places") var places: Int = 0,
    @SerializedName("isFree") var isFree: Boolean = true
): IEntity {

    fun depopulate(): Room = Room(
        type = type.id,
        description = description,
        address = address,
        floor = floor,
        places = places,
        isFree = isFree
    )
}
