package com.example.notecloud.viewmodel

import androidx.lifecycle.*
import com.example.notecloud.db.entity.ModelCloud
import com.example.notecloud.db.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCloud @Inject constructor(
    private var repository: Repository) : ViewModel() {

    val getAllCloud : LiveData<List<ModelCloud>> = repository.getAllCloud.asLiveData()

    fun getCloud(id: Int) = repository.getCloud(id).asLiveData()

    fun insertCloud(modelCloud: ModelCloud) {
        viewModelScope.launch {
            repository.insertCloud(modelCloud)
        }
    }

        fun updateCloud(modelCloud: ModelCloud) {
            viewModelScope.launch {
                repository.updateCloud(modelCloud)
            }
        }

        fun deleteCloud(modelCloud: ModelCloud) {
            viewModelScope.launch {
                repository.deleteCloud(modelCloud)
            }
        }
}



