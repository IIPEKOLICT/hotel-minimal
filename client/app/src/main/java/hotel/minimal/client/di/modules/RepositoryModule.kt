package hotel.minimal.client.di.modules

import dagger.Module
import dagger.Provides
import hotel.minimal.client.data.DataSource
import hotel.minimal.client.data.repositories.CommentRepository
import hotel.minimal.client.data.repositories.RoomRepository
import hotel.minimal.client.data.repositories.TestRepository
import hotel.minimal.client.data.repositories.TypeRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule(private val dataSource: DataSource) {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return dataSource.httpClient
    }

    @Singleton
    @Provides
    fun provideTestRepository(retrofit: Retrofit): TestRepository {
        return retrofit.create(TestRepository::class.java)
    }

    @Singleton
    @Provides
    fun provideTypeRepository(retrofit: Retrofit): TypeRepository {
        return retrofit.create(TypeRepository::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomRepository(retrofit: Retrofit): RoomRepository {
        return retrofit.create(RoomRepository::class.java)
    }

    @Singleton
    @Provides
    fun provideCommentRepository(retrofit: Retrofit): CommentRepository {
        return retrofit.create(CommentRepository::class.java)
    }
}