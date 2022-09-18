package hotel.minimal.client.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hotel.minimal.client.App
import hotel.minimal.client.databinding.RoomsListFragmentBinding
import hotel.minimal.client.presentation.adapters.RoomAdapter
import hotel.minimal.client.presentation.interfaces.IViewPagerActivity
import hotel.minimal.client.domain.models.RoomPopulated
import hotel.minimal.client.presentation.viewModels.CommentViewModel
import hotel.minimal.client.presentation.viewModels.RoomViewModel
import hotel.minimal.client.presentation.modals.RoomModal
import hotel.minimal.client.presentation.enums.Page
import javax.inject.Inject

class RoomsListFragment : Fragment(), View.OnClickListener {

    private var layout: RoomsListFragmentBinding? = null

    @Inject
    lateinit var roomViewModel: RoomViewModel

    @Inject
    lateinit var commentViewModel: CommentViewModel

    private var roomsListObserver: Observer<List<RoomPopulated>>? = null
    private var roomObserver: Observer<RoomPopulated>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).rootInjector.inject(this)
        layout = RoomsListFragmentBinding.inflate(inflater, container, false)

        val roomAdapter = RoomAdapter {
            roomViewModel.setCurrentRoom(it)
            (activity as? IViewPagerActivity)?.swipe(Page.ROOM)
        }

        with (layout!!) {
            roomRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            roomRecyclerView.adapter = roomAdapter
            roomRecyclerView.recycledViewPool.setMaxRecycledViews(
                RoomAdapter.VIEW_TYPE,
                RoomAdapter.MAX_POOL_SIZE
            )
        }

        layout!!.roomCreateButton.setOnClickListener(this)

        roomsListObserver = Observer {
            roomAdapter.submitList(it)
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
}