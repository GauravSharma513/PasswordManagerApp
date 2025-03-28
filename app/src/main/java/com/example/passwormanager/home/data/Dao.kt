package com.example.passwormanager.home.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM passwordManager")
    fun getAllPassword():Flow<List<Entity>>

    @Query("SELECT * FROM passwordManager WHERE id=:id")
    fun getPasswordfromId(id:Int): Entity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPassword(entity: Entity)

    @Query("UPDATE passwordManager SET accountName = :accountName, userName = :userName, password =:password WHERE id =:id")
    suspend fun updatePassword(accountName:String,password:String,userName:String,id:Int)

    @Delete
    suspend fun deletePassword(entity: Entity)
}