package com.setyo.similartytextapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.GetDosenResponse
import com.setyo.similartytextapp.data.remote.response.UpdateUserResponse
import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.data.remote.retrofit.ApiService
import com.setyo.similartytextapp.model.DosenModel
//import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    val updateUserResponse : LiveData<UpdateUserResponse> = repository.updateUserResponse
    val userResponse: LiveData<UserResponse> = repository.userResponse
    val getDosenResponse: LiveData<GetDosenResponse> = repository.getDosenResponse
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
fun getUserDosen(id: String) {
    viewModelScope.launch {
        repository.getUserDosen(id)
    }
}

    fun updateUserData(
        id: String, username: String, password: String, name: String, address: String, nohp: String,
        email: String
    ) {
        viewModelScope.launch {
            repository.updateDataUser(id, username, password, name, address,nohp, email)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }

    fun getDosen(): LiveData<DosenModel> {
        return repository.getDosen()
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