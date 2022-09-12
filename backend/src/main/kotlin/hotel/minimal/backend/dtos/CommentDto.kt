package hotel.minimal.backend.dtos

data class CommentDto(
    val id: Int = 0,
    val room: Int = 0,
    val content: String = ""
)