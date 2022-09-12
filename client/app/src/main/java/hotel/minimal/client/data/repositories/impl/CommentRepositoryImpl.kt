package hotel.minimal.client.data.repositories.impl

import hotel.minimal.client.data.repositories.CommentRepository
import hotel.minimal.client.domain.interfaces.ICommentRepository
import hotel.minimal.client.domain.models.Comment

class CommentRepositoryImpl(
    override val repository: CommentRepository
) : CrudRepositoryImpl<Comment, Comment>(repository), ICommentRepository