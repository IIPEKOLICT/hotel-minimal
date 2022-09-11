package hotel.minimal.client.shared

import loshica.client.BuildConfig
import hotel.minimal.client.interfaces.IApi
import hotel.minimal.client.repositories.CommentRepository
import hotel.minimal.client.repositories.MainRepository
import hotel.minimal.client.repositories.RoomRepository
import hotel.minimal.client.repositories.TypeRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api : IApi {

    private fun getHttpClient(): Retrofit {
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

    private val httpClient: Retrofit = getHttpClient()

    private const val BASE_URL: String = BuildConfig.BACKEND_URL
    private const val TIMEOUT_TIME = 30L

//    private var httpClient: Retrofit? = null

//    private fun getClient(): Retrofit {
//        if (httpClient == null) {
//            val okHttpClient = OkHttpClient()
//                .newBuilder()
//                .readTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
//                .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
//                .build()
//
//            httpClient = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//
//        return httpClient!!
//    }

    override val roomRepository: RoomRepository
        get() = httpClient.create(RoomRepository::class.java)

    override val typeRepository: TypeRepository
        get() = httpClient.create(TypeRepository::class.java)

    override val commentRepository: CommentRepository
        get() = httpClient.create(CommentRepository::class.java)

    override val mainRepository: MainRepository
        get() = httpClient.create(MainRepository::class.java)
}