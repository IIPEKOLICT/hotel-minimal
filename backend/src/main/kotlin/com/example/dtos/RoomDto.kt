package com.example.dtos

data class RoomDto(
    val id: Int = 0,
    val type: Int = 0,
    val comments: List<Int> = emptyList(),
    val description: String = "",
    val address: String = "",
    val floor: Int = 0,
    val places: Int = 1,
    val isFree: Boolean = true
)