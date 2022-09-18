package hotel.minimal.client.data

import hotel.minimal.client.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataSource {
    private const val BASE_URL: String = BuildConfig.BACKEND_URL
    private const val TIMEOUT_TIME = 30L

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .readTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
        .build()

    val httpClient: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}