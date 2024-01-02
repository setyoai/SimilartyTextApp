package com.setyo.similartytextapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.setyo.similartytextapp.data.remote.response.LoginResponse
import com.setyo.similartytextapp.data.remote.response.LoginResult
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    val userResponse: LiveData<LoginResponse> = repository.userResponse

//    fun getUserData(token: String) {
//        viewModelScope.launch {
//            repository.getUserData(token)
//        }
//    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }
}