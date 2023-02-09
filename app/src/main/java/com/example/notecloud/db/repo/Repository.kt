package com.example.notecloud.db.repo


import com.example.notecloud.db.dao.DaoCloud
import com.example.notecloud.db.entity.ModelCloud
import javax.inject.Inject


class Repository @Inject constructor(private val daoCloud: DaoCloud) {

        val getAllCloud = daoCloud.getAllCloud()

        fun getCloud(id: Int) = daoCloud.getCloud(id)

        suspend fun insertCloud(modelCloud: ModelCloud) = daoCloud.insertCloud(modelCloud)

        suspend fun updateCloud(modelCloud: ModelCloud) = daoCloud.updateCloud(modelCloud)

        suspend fun deleteCloud(modelCloud: ModelCloud) = daoCloud.deleteCloud(modelCloud)

}



