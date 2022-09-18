package hotel.minimal.client.presentation.adapters.diffCallbacks

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import hotel.minimal.client.domain.models.RoomPopulated

class RoomDiffCallback : ItemCallback<RoomPopulated>() {

    override fun areItemsTheSame(oldItem: RoomPopulated, newItem: RoomPopulated): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RoomPopulated, newItem: RoomPopulated): Boolean {
        return oldItem == newItem
    }
}