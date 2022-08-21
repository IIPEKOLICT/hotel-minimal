package com.example.dao.interfaces

import org.jetbrains.exposed.dao.IntEntity

interface IBaseService<T: IntEntity> {
    suspend fun getAll(): List<T>
    suspend fun getById(id: Int): T
    suspend fun delete(id: Int)
}