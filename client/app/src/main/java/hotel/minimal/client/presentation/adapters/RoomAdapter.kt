package hotel.minimal.client.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import hotel.minimal.client.databinding.RoomCardBinding
import hotel.minimal.client.domain.models.RoomPopulated
import hotel.minimal.client.presentation.adapters.diffCallbacks.RoomDiffCallback
import hotel.minimal.client.presentation.adapters.viewHolders.RoomCardViewHolder

class RoomAdapter(
    private val onPick: (position: Int) -> Unit
) : ListAdapter<RoomPopulated, RoomCardViewHolder>(RoomDiffCallback()) {

    private var layout: RoomCardBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomCardViewHolder {
        layout = RoomCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RoomCardViewHolder(layout!!, onPick)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RoomCardViewHolder, position: Int) {
        val room: RoomPopulated = getItem(position)

        with (holder.layout) {
            roomCardType.text = "Type: ${room.type.name}"
            roomCardFloor.text = "Floor: ${room.floor}"
            roomCardStatus.text = "Status: ${if (room.isFree) "free" else "booked"}"
            root.setOnClickListener(holder)
        }
    }

    override fun getItemViewType(position: Int): Int = VIEW_TYPE

    companion object {
        const val VIEW_TYPE = 1
        const val MAX_POOL_SIZE = 15
    }
}