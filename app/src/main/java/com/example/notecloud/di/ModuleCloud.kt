package com.example.notecloud.di

import android.content.Context
import androidx.room.Room
import com.example.notecloud.db.DataBaseCloud
import com.example.notecloud.db.entity.Converters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ModuleCloud {

    @Singleton
    @Provides
    fun getNoteDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, DataBaseCloud::class.java, "cloud_tablet")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun getDaoCloud(db: DataBaseCloud) = db.daoCloud()


}