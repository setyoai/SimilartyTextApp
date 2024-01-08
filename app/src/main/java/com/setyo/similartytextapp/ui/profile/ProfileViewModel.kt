package com.setyo.similartytextapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.UpdateUserResponse
import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.data.remote.retrofit.ApiService
//import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    val updateUserResponse : LiveData<UpdateUserResponse> = repository.updateUserResponse
    val userResponse: LiveData<UserResponse> = repository.userResponse
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getUserData(id: String) {
        viewModelScope.launch {
            repository.getUserData(id)
        }
    }
//    fun updateUser(token: String, avatar_image: MultipartBody.Part) {
//        viewModelScope.launch {
//            repository.updateUser(token, avatar_image)
//        }
//    }

    fun updateUserData( id: String, username: String, password: String, name: String, address: String,) {
        viewModelScope.launch {
            repository.updateDataUser(id, username, password, name, address,)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }

    fun getApiService(): ApiService {
        return repository.getApiService()
    }
    fun logoutUser() {
        viewModelScope.launch {
            repository.logoutUser()
        }
    }
}