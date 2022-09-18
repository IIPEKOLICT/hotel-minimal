package hotel.minimal.client.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hotel.minimal.client.databinding.RoomsListFragmentBinding
import hotel.minimal.client.presentation.adapters.RoomAdapter
import hotel.minimal.client.presentation.interfaces.IMainActivity
import hotel.minimal.client.presentation.interfaces.IPickHandler
import hotel.minimal.client.domain.models.RoomPopulated
import hotel.minimal.client.presentation.viewModels.CommentViewModel
import hotel.minimal.client.presentation.viewModels.RoomViewModel
import hotel.minimal.client.presentation.modals.RoomModal
import hotel.minimal.client.presentation.enums.Page

class RoomsListFragment : Fragment(), View.OnClickListener, IPickHandler {

    private var layout: RoomsListFragmentBinding? = null
    private val roomViewModel: RoomViewModel by activityViewModels()
    private val commentViewModel: CommentViewModel by activityViewModels()

    private var roomsListObserver: Observer<List<RoomPopulated>>? = null
    private var roomObserver: Observer<RoomPopulated>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = RoomsListFragmentBinding.inflate(inflater, container, false)
        val roomAdapter = RoomAdapter(this)

        with (layout!!) {
            roomRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            roomRecyclerView.adapter = roomAdapter
        }

        layout!!.roomCreateButton.setOnClickListener(this)

        roomsListObserver = Observer {
            roomAdapter.update(it)
        }

        roomObserver = Observer {
            commentViewModel.setRoomId(it.id)
            commentViewModel.loadComments()
        }

        return layout?.root
    }

    override fun onClick(v: View?) {
        layout?.let {
            when(v) {
                it.roomCreateButton -> {
                    RoomModal
                        .newInstance(false)
                        .show(requireActivity().supportFragmentManager, null)
                }
                else -> {}
            }
        }
    }

    override fun onStart() {
        super.onStart()
        roomsListObserver?.let { roomViewModel.roomsList.observe(this, it) }
        roomObserver?.let { roomViewModel.room.observe(this, it) }
    }

    override fun onStop() {
        super.onStop()
        roomsListObserver?.let { roomViewModel.roomsList.removeObserver(it) }
        roomObserver?.let { roomViewModel.room.removeObserver(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        layout = null
    }

    override fun onPickCard(position: Int) {
        roomViewModel.setCurrentRoom(position)
        (activity as? IMainActivity)?.swipe(Page.ROOM)
    }
}