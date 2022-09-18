package hotel.minimal.client.presentation.adapters.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hotel.minimal.client.databinding.CommentCardBinding

class CommentCardViewHolder(
    val layout: CommentCardBinding,
    private val onPick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(layout.root), View.OnClickListener {

    override fun onClick(v: View?) {
        onPick(adapterPosition)
    }
}