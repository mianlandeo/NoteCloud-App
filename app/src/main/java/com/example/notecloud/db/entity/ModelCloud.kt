package com.example.notecloud.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "cloud_tablet")
data class ModelCloud(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "title")
    val cloudTitle: String,

    @ColumnInfo(name = "description")
    val cloudDescription: String,

    @ColumnInfo(name = "priority")
    val priorityCloud: Int,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "date")
    val dateCloud: String,
    @ColumnInfo(name = "up_date")
    var upDateCloud: String?,
)