package hotel.minimal.client.data

import hotel.minimal.client.data.repositories.CommentRepository
import hotel.minimal.client.data.repositories.RoomRepository
import hotel.minimal.client.data.repositories.TestRepository
import hotel.minimal.client.data.repositories.TypeRepository
import hotel.minimal.client.data.repositories.impl.CommentRepositoryImpl
import hotel.minimal.client.data.repositories.impl.RoomRepositoryImpl
import hotel.minimal.client.data.repositories.impl.TestRepositoryImpl
import hotel.minimal.client.data.repositories.impl.TypeRepositoryImpl
import hotel.minimal.client.domain.interfaces.ICommentRepository
import hotel.minimal.client.domain.interfaces.IRoomRepository
import hotel.minimal.client.domain.interfaces.ITestRepository
import hotel.minimal.client.domain.interfaces.ITypeRepository
import loshica.client.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {
    private const val BASE_URL: String = BuildConfig.BACKEND_URL
    private const val TIMEOUT_TIME = 30L

    private var httpClient: Retrofit? = null

    private fun getClient(): Retrofit {
        if (httpClient == null) {
            val okHttpClient = OkHttpClient()
                .newBuilder()
                .readTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
                .build()

            httpClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return httpClient!!
    }

    val typeRepository: ITypeRepository
        get() = TypeRepositoryImpl(
            getClient().create(TypeRepository::class.java)
        )

    val roomRepository: IRoomRepository
        get() = RoomRepositoryImpl(
            getClient().create(RoomRepository::class.java)
        )

    val commentRepository: ICommentRepository
        get() = CommentRepositoryImpl(
            getClient().create(CommentRepository::class.java)
        )

    val testRepository: ITestRepository
        get() = TestRepositoryImpl(
            getClient().create(TestRepository::class.java)
        )
}