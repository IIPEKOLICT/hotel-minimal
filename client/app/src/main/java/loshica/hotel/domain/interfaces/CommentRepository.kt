package loshica.hotel.domain.interfaces

import loshica.hotel.domain.models.Type

interface CommentRepository {
    fun getTypes(): List<Type>
}