package com.example.notecloud.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.notecloud.db.dao.DaoCloud
import com.example.notecloud.db.entity.Converters
import com.example.notecloud.db.entity.ModelCloud


@Database(entities = [ModelCloud::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DataBaseCloud: RoomDatabase() {
    abstract fun daoCloud() : DaoCloud
}