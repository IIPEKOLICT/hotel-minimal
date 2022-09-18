package hotel.minimal.client.domain.interfaces

import androidx.lifecycle.LiveData

interface ITestService {
    val liveData: LiveData<Boolean>

    suspend fun healthCheck()
}