package com.setyo.similartytextapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.ResultDafSkripsiResponse
import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    val resultDafSkripsiResponse: LiveData<ResultDafSkripsiResponse> = repository.resultDafSkripsiResponse
    val userResponse: LiveData<UserResponse> = repository.userResponse

    fun getUserData(id: String) {
        viewModelScope.launch {
            repository.getUserData(id)
        }
    }

    fun getDafSkripsi(id: String) {
        viewModelScope.launch {
            repository.getUserDafSkripsi(id)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }
}