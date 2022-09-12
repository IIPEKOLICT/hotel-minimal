package hotel.minimal.client.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import loshica.client.databinding.CurrentRoomFragmentBinding
import hotel.minimal.client.interfaces.IMainActivity
import hotel.minimal.client.domain.models.RoomPopulated
import hotel.minimal.client.shared.Position
import hotel.minimal.client.viewModels.RoomViewModel
import hotel.minimal.client.views.modals.RoomModal

class CurrentRoomFragment : Fragment(), View.OnClickListener {

    private var layout: CurrentRoomFragmentBinding? = null
    private val roomViewModel: RoomViewModel by activityViewModels()

    private var currentRoomObserver: Observer<RoomPopulated>? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = CurrentRoomFragmentBinding.inflate(inflater, container, false)

        with (layout!!) {
            currentRoomObserver = Observer {
                println(it.isFree)

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
        currentRoomObserver?.let { roomViewModel.currentRoom.observe(this, it) }
    }

    override fun onStop() {
        super.onStop()
        currentRoomObserver?.let { roomViewModel.currentRoom.removeObserver(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        layout = null
    }

    override fun onClick(v: View?) {
        layout?.let {
            when(v) {
                it.bookRoomButton -> {
                    roomViewModel.setIsEdit(true)
                    val currentRoom: RoomPopulated = roomViewModel.getCurrentRoom()

                    if (currentRoom.id != -1) {
                        currentRoom.isFree = !currentRoom.isFree
                        roomViewModel.onSubmit(currentRoom.depopulate())
                    } else {}
                }
                it.deleteRoomButton -> {
                    roomViewModel.setIsEdit(false)
                    roomViewModel.deleteRoom()
                    (activity as? IMainActivity)?.swipe(Position.ROOMS)
                }
                it.editRoomButton -> {
                    roomViewModel.setIsEdit(true)
                    RoomModal().show(requireActivity().supportFragmentManager, null)
                }
                else -> {}
            }
        }
    }
}