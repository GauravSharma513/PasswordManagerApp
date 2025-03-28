package com.example.passwormanager.home.domain

import com.example.passwormanager.home.data.Entity
import com.example.passwormanager.home.data.Dao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject



class Repository @Inject constructor(
   private val dao: Dao
) {

    suspend fun addPassword(entity: Entity) = dao.addPassword(entity)
    suspend fun updatePassword(entity: Entity) = dao.updatePassword(
        password = entity.password,
        accountName = entity.accountName,
        userName = entity.userName,
        id = entity.id
    )
    suspend fun deletedPassword(entity: Entity)= dao.deletePassword(entity)
    fun getAllPassword(): Flow<List<Entity>> = dao.getAllPassword().flowOn(Dispatchers.IO).conflate()

}