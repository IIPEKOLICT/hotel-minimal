package hotel.minimal.client.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import hotel.minimal.client.presentation.fragments.CommentsListFragment
import hotel.minimal.client.presentation.fragments.RoomFragment
import hotel.minimal.client.presentation.fragments.RoomsListFragment

class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> RoomsListFragment()
            1 -> RoomFragment()
            else -> CommentsListFragment()
        }
    }
}