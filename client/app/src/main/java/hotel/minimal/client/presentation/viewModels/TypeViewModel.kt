package hotel.minimal.client.presentation.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hotel.minimal.client.data.DataSource
import hotel.minimal.client.domain.models.Type
import kotlinx.coroutines.*
import hotel.minimal.client.domain.useCases.type.GetTypesListUseCase

class TypeViewModel(override val app: Application): BaseViewModel(app) {

    private val getTypesListUseCase = GetTypesListUseCase(DataSource.typeService)

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