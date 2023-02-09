package com.example.notecloud.db.dao

import androidx.room.*
import com.example.notecloud.db.entity.ModelCloud
import kotlinx.coroutines.flow.Flow


@Dao
interface DaoCloud {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCloud(modelCloud: ModelCloud)

    @Delete
    suspend fun deleteCloud(modelCloud: ModelCloud)

    @Query("SELECT * FROM cloud_tablet ORDER BY id ASC")
    fun getAllCloud(): Flow<List<ModelCloud>>

    @Query("SELECT * FROM cloud_tablet WHERE id = :id")
    fun getCloud(id: Int): Flow<ModelCloud>

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun updateCloud(modelCloud: ModelCloud)

}