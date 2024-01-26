package com.setyo.similartytextapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.GetDosenResponse
import com.setyo.similartytextapp.data.remote.response.ResultDafSkripsiResponse
import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.model.DafSkripsiModel
import com.setyo.similartytextapp.model.DosenModel
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    val userResponse: LiveData<UserResponse> = repository.userResponse
    val getDosenResponse: LiveData<GetDosenResponse> = repository.getDosenResponse


    fun getUserData(id: String) {
        viewModelScope.launch {
            repository.getUserData(id)
        }
    }

    fun getUserDosen(id: String) {
        viewModelScope.launch {
            repository.getUserDosen(id)
        }
    }


    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }

    fun getDosen(): LiveData<DosenModel> {
        return repository.getDosen()
    }

}