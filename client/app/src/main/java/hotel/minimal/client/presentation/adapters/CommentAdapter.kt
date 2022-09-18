package hotel.minimal.client.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import hotel.minimal.client.databinding.CommentCardBinding
import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.presentation.adapters.diffCallbacks.CommentDiffCallback
import hotel.minimal.client.presentation.adapters.viewHolders.CommentCardViewHolder

class CommentAdapter(
    private val onPick: (position: Int) -> Unit
) : ListAdapter<Comment, CommentCardViewHolder>(CommentDiffCallback()) {

    private var layout: CommentCardBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentCardViewHolder {
        layout = CommentCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CommentCardViewHolder(layout!!, onPick)
    }

    override fun onBindViewHolder(holder: CommentCardViewHolder, position: Int) {
        with (holder.layout) {
            commentCardContent.text = getItem(position).content
            root.setOnClickListener(holder)
        }
    }

    override fun getItemViewType(position: Int): Int = VIEW_TYPE

    companion object {
        const val VIEW_TYPE = 1
        const val MAX_POOL_SIZE = 15
    }
}