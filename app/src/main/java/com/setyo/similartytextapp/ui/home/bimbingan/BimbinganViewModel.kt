package com.setyo.similartytextapp.ui.home.bimbingan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.BimbinganResponse
import com.setyo.similartytextapp.data.remote.response.CreateBimbinganResponse
import com.setyo.similartytextapp.data.remote.response.DafSkripsiResponse
import com.setyo.similartytextapp.data.remote.response.DosbingMhsResponse
import com.setyo.similartytextapp.data.remote.response.ResultDafSkripsiResponse
import com.setyo.similartytextapp.model.DafSkripsiModel
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BimbinganViewModel(private val repository: UserRepository): ViewModel() {
    val dosbingMhsResponse: LiveData<DosbingMhsResponse> = repository.dosbingMhsResponse
    val bimbinganResponse: LiveData<BimbinganResponse> = repository.bimbinganResponse
    val createBimbinganResponse: LiveData<CreateBimbinganResponse> = repository.createBimbinganResponse
    val resultDafSkripsiResponse: LiveData<ResultDafSkripsiResponse> = repository.resultDafSkripsiResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getMhsDosbing(id: String) {
        viewModelScope.launch {
            repository.getDataMhsDosbing(id)
        }
    }

    fun sendBimbingan(
        dosbingid: RequestBody,
        mhsnim: RequestBody,
//        bab: RequestBody,
        ket: RequestBody,
//        file: MultipartBody.Part,
        dosenid: RequestBody

    ) {
        viewModelScope.launch {
            repository.bimbinganMhs( dosbingid, mhsnim, ket, dosenid )
        }
    }

    fun getDataBimbingan(dosbingid: String, dosenid: String) {
        viewModelScope.launch {
            repository.getDataBimbingan(dosbingid, dosenid)
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