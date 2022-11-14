package com.example.loveapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loveapp.model.LoveModel
import com.example.loveapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class LoveViewModel @Inject constructor(private val repository:Repository) :ViewModel() {

    fun getLiveLoveModel(firstName: String,secondName: String): MutableLiveData<LoveModel>{
        return repository.getMutableLiveDataOfLoveModel(firstName,secondName)
    }
}