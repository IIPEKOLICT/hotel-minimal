package hotel.minimal.client.domain.useCases

import androidx.lifecycle.LiveData
import hotel.minimal.client.domain.interfaces.ICrudService

abstract class BaseGetListUseCase<E, D>(private val service: ICrudService<E, D>) {

    val liveData: LiveData<List<E>>
        get() = service.liveData
}