package com.example.data.services

import com.example.data.interfaces.IBaseService
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

abstract class BaseService<M: IntEntity, C : IntEntityClass<M>>(protected val model: C) : IBaseService<M> {
    private val modelClassName: String
        get() {
            return try {
                val classNameParsed: List<String> = model.javaClass.declaringClass.toString().split(".")
                classNameParsed[classNameParsed.size - 1]
            } catch (e: Exception) {
                "Entity"
            }
        }

    protected suspend fun <T> query(callback: suspend () -> T): T {
        return newSuspendedTransaction(Dispatchers.IO) { callback() }
    }

    override suspend fun getAll(): List<M> {
        return query { model.all().toList() }
    }

    override suspend fun getById(id: Int): M {
        return query {
            model.findById(id) ?: throw RuntimeException("$modelClassName with id '$id' not found")
        }
    }

    override suspend fun delete(id: Int) {
        return query { model.findById(id)?.delete() }
    }
}