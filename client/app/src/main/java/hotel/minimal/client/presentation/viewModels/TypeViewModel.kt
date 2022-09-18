package hotel.minimal.client.presentation.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hotel.minimal.client.domain.models.Type
import kotlinx.coroutines.*
import hotel.minimal.client.domain.useCases.type.GetTypesListUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TypeViewModel @Inject constructor(
    override val app: Application,
    private val getTypesListUseCase: GetTypesListUseCase
): BaseViewModel(app) {

    val typesList: LiveData<List<Type>>
        get() = getTypesListUseCase.liveData

    init {
        loadTypes()
    }

    private fun loadTypes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getTypesListUseCase.getTypesList()
            } catch (e: Exception) {
                onError(e.message)
            }
        }
    }
}