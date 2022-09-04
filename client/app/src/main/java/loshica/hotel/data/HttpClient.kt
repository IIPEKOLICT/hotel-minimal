package loshica.hotel.data

import loshica.hotel.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {
    private const val BASE_URL: String = BuildConfig.BACKEND_URL;
    private var httpClient: Retrofit? = null

    private fun getClient(): Retrofit {
        if (httpClient == null) {
            httpClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return httpClient!!
    }

    val typeRepository: TypeRepository
        get() = getClient().create(TypeRepository::class.java)
}