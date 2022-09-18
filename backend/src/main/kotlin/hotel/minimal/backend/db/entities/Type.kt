package hotel.minimal.backend.db.entities

import hotel.minimal.backend.dtos.TypeDto
import hotel.minimal.backend.db.interfaces.IEntity
import hotel.minimal.backend.db.tables.Types
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

class Type(id: EntityID<Int>) : IntEntity(id), IEntity<TypeDto, TypeDto> {
    var name: String by Types.name
    var options: String by Types.options
    var price: Int by Types.price

    override fun toDto(): TypeDto {
        val type: Type = this

        return transaction {
            TypeDto(
                id = type.id.value,
                name = type.name,
                options = type.options,
                price = type.price
            )
        }
    }

    override fun toPopulatedDto(): TypeDto = toDto()

    companion object : IntEntityClass<Type>(Types)
}