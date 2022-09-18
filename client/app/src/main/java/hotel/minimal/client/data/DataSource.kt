package hotel.minimal.client.data

import hotel.minimal.client.BuildConfig
import hotel.minimal.client.data.repositories.CommentRepository
import hotel.minimal.client.data.repositories.RoomRepository
import hotel.minimal.client.data.repositories.TestRepository
import hotel.minimal.client.data.repositories.TypeRepository
import hotel.minimal.client.data.services.CommentService
import hotel.minimal.client.data.services.RoomService
import hotel.minimal.client.data.services.TestService
import hotel.minimal.client.data.services.TypeService
import hotel.minimal.client.domain.interfaces.ICommentService
import hotel.minimal.client.domain.interfaces.IRoomService
import hotel.minimal.client.domain.interfaces.ITestService
import hotel.minimal.client.domain.interfaces.ITypeService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataSource {
    private const val BASE_URL: String = BuildConfig.BACKEND_URL
    private const val TIMEOUT_TIME = 30L

    private val httpClient: Retrofit = initClient()

    private fun initClient(): Retrofit {
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .readTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val typeService: ITypeService = TypeService(
        httpClient.create(TypeRepository::class.java)
    )

    val roomService: IRoomService = RoomService(
        httpClient.create(RoomRepository::class.java)
    )

    val commentService: ICommentService = CommentService(
        httpClient.create(CommentRepository::class.java)
    )

    val testService: ITestService = TestService(
        httpClient.create(TestRepository::class.java)
    )
}