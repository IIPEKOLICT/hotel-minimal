package hotel.minimal.client.data.repositories.impl

import hotel.minimal.client.data.repositories.TypeRepository
import hotel.minimal.client.domain.interfaces.ITypeRepository
import hotel.minimal.client.domain.models.Type

class TypeRepositoryImpl(
    override val repository: TypeRepository
) : CrudRepositoryImpl<Type, Type>(repository), ITypeRepository