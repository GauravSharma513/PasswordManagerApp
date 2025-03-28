package com.example.passwormanager.home.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Entity::class],
    version = 3,
    exportSchema = false

)
abstract class Database:RoomDatabase() {
    abstract fun PasswordDao(): Dao
}