package hotel.minimal.client.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import hotel.minimal.client.databinding.RoomFragmentBinding
import hotel.minimal.client.presentation.interfaces.IMainActivity
import hotel.minimal.client.domain.models.RoomPopulated
import hotel.minimal.client.presentation.viewModels.RoomViewModel
import hotel.minimal.client.presentation.modals.RoomModal
import hotel.minimal.client.presentation.enums.Page

class RoomFragment : Fragment(), View.OnClickListener {

    private var layout: RoomFragmentBinding? = null
    private val roomViewModel: RoomViewModel by activityViewModels()

    private var roomObserver: Observer<RoomPopulated>? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = RoomFragmentBinding.inflate(inflater, container, false)

        with (layout!!) {
            roomObserver = Observer {
                roomTypeName.text = "Type: ${it.type.name}"
                roomTypeOptions.text = "Options: ${it.type.options}"
                roomTypePrice.text = "Price: ${it.type.price}$"
                roomAddress.text = "Address: ${it.address}"
                roomDescription.text = "Description: ${it.description}"
                roomFloor.text = "Floor: ${it.floor}"
                roomPlaces.text = "Places: ${it.places}"
                roomStatus.text = "Status: ${if (it.isFree) "free" else "booked"}"
            }
        }

        layout!!.editRoomButton.setOnClickListener(this)
        layout!!.bookRoomButton.setOnClickListener(this)
        layout!!.deleteRoomButton.setOnClickListener(this)

        return layout?.root
    }

    override fun onStart() {
        super.onStart()
        roomObserver?.let { roomViewModel.room.observe(this, it) }
    }

    override fun onStop() {
        super.onStop()
        roomObserver?.let { roomViewModel.room.removeObserver(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        layout = null
    }

    override fun onClick(v: View?) {
        layout?.let {
            when(v) {
                it.bookRoomButton -> {
                    val currentRoom: RoomPopulated = roomViewModel.getCurrentRoom()
                    currentRoom.isFree = !currentRoom.isFree
                    roomViewModel.updateRoom(currentRoom.depopulate())
                }
                it.deleteRoomButton -> {
                    roomViewModel.deleteRoom()
                    (activity as? IMainActivity)?.swipe(Page.ROOMS_LIST)
                }
                it.editRoomButton -> {
                    RoomModal
                        .newInstance(true)
                        .show(requireActivity().supportFragmentManager, null)
                }
                else -> {}
            }
        }
    }
}