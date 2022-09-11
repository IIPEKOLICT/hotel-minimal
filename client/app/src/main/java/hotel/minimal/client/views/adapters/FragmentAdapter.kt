package hotel.minimal.client.views.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import hotel.minimal.client.views.fragments.CommentFragment
import hotel.minimal.client.views.fragments.CurrentRoomFragment
import hotel.minimal.client.views.fragments.RoomFragment

class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> RoomFragment()
            1 -> CurrentRoomFragment()
            else -> CommentFragment()
        }
    }
}