package com.setyo.similartytextapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.LoginResponse
import com.setyo.similartytextapp.data.remote.response.LoginUserResponse
import com.setyo.similartytextapp.model.DosenModel
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    val loginResponse: LiveData<LoginResponse> = repository.loginResponse
    val loginUserResponse: LiveData<LoginUserResponse> = repository.loginUserResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast
    private var _isLoginState: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoginState: LiveData<Boolean> = _isLoginState

    init {
        viewModelScope.launch {
            repository.getLoginState().collect { isLogin ->
                _isLoginState.value = isLogin
            }
        }
    }

    fun loginUser(username: String, password: String) {
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

    fun getLoginState() {

    }
}

