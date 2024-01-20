package com.setyo.similartytextapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.LoginResponse
import com.setyo.similartytextapp.data.remote.response.LoginUserResponse
import com.setyo.similartytextapp.model.DosenModel
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository): ViewModel() {
    val loginResponse: LiveData<LoginResponse> = repository.loginResponse
    val loginUserResponse: LiveData<LoginUserResponse> = repository.loginUserResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun loginUser (username: String, password: String) {
        viewModelScope.launch {
            repository.loginUser(username, password)
        }
    }

    fun loginUserDosen(username: String, password: String) {
        viewModelScope.launch {
            repository.loginUserDosen(username, password)
        }
    }

    fun getLoginUser(user: UserModel) {
        viewModelScope.launch {
            repository.getLoginUser(user)
        }
    }

    fun getLoginDosen(dosen: DosenModel) {
        viewModelScope.launch {
            repository.getLoginDosen(dosen)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }


    fun getToken() {
        viewModelScope.launch {
            repository.getToken()
        }
    }
}

