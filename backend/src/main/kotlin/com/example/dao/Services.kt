package com.example.dao

import com.example.dao.services.CommentService
import com.example.dao.services.RoomService
import com.example.dao.services.TypeService

object Services {
    val typeService = TypeService()
    val roomService = RoomService()
    val commentService = CommentService()
}