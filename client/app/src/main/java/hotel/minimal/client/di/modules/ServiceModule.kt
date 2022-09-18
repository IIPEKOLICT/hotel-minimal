package hotel.minimal.client.di.modules

import dagger.Binds
import dagger.Module
import hotel.minimal.client.data.services.CommentService
import hotel.minimal.client.data.services.RoomService
import hotel.minimal.client.data.services.TestService
import hotel.minimal.client.data.services.TypeService
import hotel.minimal.client.domain.interfaces.ICommentService
import hotel.minimal.client.domain.interfaces.IRoomService
import hotel.minimal.client.domain.interfaces.ITestService
import hotel.minimal.client.domain.interfaces.ITypeService

@Module
abstract class ServiceModule {

    @Binds
    abstract fun bindTestService(testService: TestService): ITestService

    @Binds
    abstract fun bindTypeService(typeService: TypeService): ITypeService

    @Binds
    abstract fun bindRoomService(roomService: RoomService): IRoomService

    @Binds
    abstract fun bindCommentService(commentService: CommentService): ICommentService
}