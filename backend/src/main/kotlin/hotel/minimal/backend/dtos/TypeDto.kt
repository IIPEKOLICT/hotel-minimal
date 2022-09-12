package hotel.minimal.backend.dtos

data class TypeDto(
    val id: Int = 0,
    val name: String = "",
    val options: String = "no options",
    val price: Int = 0,
)