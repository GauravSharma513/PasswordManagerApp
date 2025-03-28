package com.example.passwormanager.home.di

import android.content.Context
import androidx.room.Room
import com.example.passwormanager.home.data.Dao
import com.example.passwormanager.home.data.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideTodoDao(database: Database): Dao = database.PasswordDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : Database =
        Room.databaseBuilder(
            context, Database::class.java,
            "passwordManager"
        ).fallbackToDestructiveMigration().build()


}