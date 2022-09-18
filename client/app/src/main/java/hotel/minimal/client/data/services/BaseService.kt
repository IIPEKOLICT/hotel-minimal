package hotel.minimal.client.data.services

import retrofit2.Response

abstract class BaseService {

    protected fun <R> parseResponse(response: Response<R>): R {
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw RuntimeException(response.message() ?: "Something went wrong")
        }
    }
}