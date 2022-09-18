package hotel.minimal.client.domain

import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.domain.models.RoomPopulated
import hotel.minimal.client.domain.models.Type

object DefaultValue {
    private val TYPE = Type(name = "example type", price = 123)

    val ROOM = RoomPopulated(
        type = TYPE,
        description = "example description",
        address = "example address",
        floor = 123,
        places = 123
    )

    val COMMENT = Comment(content = "example text")
}