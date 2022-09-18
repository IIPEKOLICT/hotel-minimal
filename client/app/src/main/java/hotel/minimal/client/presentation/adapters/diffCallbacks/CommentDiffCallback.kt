package hotel.minimal.client.presentation.adapters.diffCallbacks

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import hotel.minimal.client.domain.models.Comment

class CommentDiffCallback : ItemCallback<Comment>() {

    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}