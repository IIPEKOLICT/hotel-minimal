package hotel.minimal.client.di.components

import dagger.Component
import hotel.minimal.client.di.modules.ApplicationModule
import hotel.minimal.client.di.modules.RepositoryModule
import hotel.minimal.client.di.modules.ServiceModule
import hotel.minimal.client.presentation.MainActivity
import hotel.minimal.client.presentation.fragments.CommentsListFragment
import hotel.minimal.client.presentation.fragments.RoomFragment
import hotel.minimal.client.presentation.fragments.RoomsListFragment
import hotel.minimal.client.presentation.modals.CommentModal
import hotel.minimal.client.presentation.modals.RoomModal
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ServiceModule::class, ApplicationModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(commentsListFragment: CommentsListFragment)
    fun inject(roomsListFragment: RoomsListFragment)
    fun inject(roomFragment: RoomFragment)
    fun inject(commentModal: CommentModal)
    fun inject(roomModal: RoomModal)
}