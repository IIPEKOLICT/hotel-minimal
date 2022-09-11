package hotel.minimal.client.interfaces

import hotel.minimal.client.repositories.CommentRepository
import hotel.minimal.client.repositories.MainRepository
import hotel.minimal.client.repositories.RoomRepository
import hotel.minimal.client.repositories.TypeRepository

interface IApi {
    val roomRepository: RoomRepository
    val typeRepository: TypeRepository
    val commentRepository: CommentRepository
    val mainRepository: MainRepository
}