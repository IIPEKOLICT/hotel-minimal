package hotel.minimal.client.presentation.adapters.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hotel.minimal.client.databinding.RoomCardBinding

class RoomCardViewHolder(
    val layout: RoomCardBinding,
    private val onPick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(layout.root), View.OnClickListener {

    override fun onClick(v: View?) {
        onPick(adapterPosition)
    }
}