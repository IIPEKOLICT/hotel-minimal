package hotel.minimal.client.data.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hotel.minimal.client.data.repositories.TestRepository
import hotel.minimal.client.domain.interfaces.ITestService
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestService @Inject constructor(
    private val repository: TestRepository
) : BaseService(), ITestService {

    private val hasConnection: MutableLiveData<Boolean> = MutableLiveData(true)

    override val liveData: LiveData<Boolean>
        get() = hasConnection

    override suspend fun healthCheck() {
        try {
            hasConnection.postValue(
                parseResponse(repository.healthCheck()).status == "ok"
            )
        } catch (e: Exception) {
            delay(2000)
            hasConnection.postValue(false)
        }
    }
}