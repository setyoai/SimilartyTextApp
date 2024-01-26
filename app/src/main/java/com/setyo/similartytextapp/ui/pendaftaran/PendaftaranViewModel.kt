package com.setyo.similartytextapp.ui.pendaftaran

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.ResultDafSkripsiResponse
import com.setyo.similartytextapp.model.DafSkripsiModel
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import kotlinx.coroutines.launch

class PendaftaranViewModel(private val repository: UserRepository) : ViewModel() {

    val resultDafSkripsiResponse: LiveData<ResultDafSkripsiResponse> = repository.resultDafSkripsiResponse

    fun getDafSkripsi(id: String) {
        viewModelScope.launch {
            repository.getUserDafSkripsi(id)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }


}