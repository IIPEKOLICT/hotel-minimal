package hotel.minimal.backend.dao

import hotel.minimal.backend.dao.services.CommentService
import hotel.minimal.backend.dao.services.RoomService
import hotel.minimal.backend.dao.services.TypeService

object Services {
    val typeService = TypeService()
    val roomService = RoomService()
    val commentService = CommentService()
}