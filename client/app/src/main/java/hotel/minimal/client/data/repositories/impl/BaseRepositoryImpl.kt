package hotel.minimal.client.data.repositories.impl

import retrofit2.Response

abstract class BaseRepositoryImpl {

    protected fun <R> parseResponse(response: Response<R>): R {
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw RuntimeException(response.message() ?: "Something went wrong")
        }
    }
}