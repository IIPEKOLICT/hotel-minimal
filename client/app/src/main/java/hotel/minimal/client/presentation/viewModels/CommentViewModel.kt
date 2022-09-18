package hotel.minimal.client.presentation.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hotel.minimal.client.data.DataSource
import kotlinx.coroutines.*
import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.domain.DefaultValue
import hotel.minimal.client.domain.useCases.comment.*

class CommentViewModel(override val app: Application): BaseViewModel(app) {

    private val currentComment: MutableLiveData<Comment> = MutableLiveData(DefaultValue.COMMENT)

    private val getRoomCommentsListUseCase = GetRoomCommentsListUseCase(DataSource.commentService)
    private val createCommentUseCase = CreateCommentUseCase(DataSource.commentService)
    private val updateCommentUseCase = UpdateCommentUseCase(DataSource.commentService)
    private val deleteCommentUseCase = DeleteCommentUseCase(DataSource.commentService)

    val commentsList: LiveData<List<Comment>>
        get() = getRoomCommentsListUseCase.liveData

    val comment: LiveData<Comment>
        get() = currentComment

    private var roomId: Int = 0

    private val commentId: Int
        get() = currentComment.value?.id ?: throw RuntimeException("No commentId")

    fun loadComments() {
        if (roomId < 1) return

        viewModelScope.launch(Dispatchers.IO) {
            try {
                getRoomCommentsListUseCase.getRoomCommentsList(roomId)
            } catch (e: Exception) {
                onError(e.message)
            }
        }
    }

    fun setCurrentComment(index: Int?) {
        commentsList.value?.let {
            currentComment.value = if (index == null) DefaultValue.COMMENT else it[index]
        }
    }

    fun setRoomId(value: Int) {
        roomId = value
    }

    fun createComment(content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                createCommentUseCase.createComment(roomId, content)
                showToast("Comment created")
            } catch (e: Exception) {
                onError(e.message)
            }
        }
    }

    fun updateComment(content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateCommentUseCase.updateComment(commentId, content)
                showToast("Comment updated")
            } catch (e: Exception) {
                onError(e.message)
            }
        }
    }

    fun deleteComment() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteCommentUseCase.deleteComment(commentId)
                showToast("Comment deleted")
            } catch (e: Exception) {
                onError(e.message)
            }
        }
    }
}