package com.example.dtos

data class RoomPopulatedDto(
    val id: Int = 0,
    val type: TypeDto,
    val comments: List<CommentDto> = emptyList(),
    val description: String = "",
    val address: String = "",
    val floor: Int = 0,
    val places: Int = 1,
    val isFree: Boolean = true
)