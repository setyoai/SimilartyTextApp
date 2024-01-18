package com.setyo.similartytextapp.ui.skripsi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.DafSkripsiResponse
import com.setyo.similartytextapp.data.remote.response.DaftarSeminarResponse
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class SkripsiViewModel(private val repository: UserRepository): ViewModel() {
    val dafSkripsiResponse: LiveData<DafSkripsiResponse> = repository.dafSkripsiResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun uploadFileSkripsi(
        nim: RequestBody,
        krs: MultipartBody.Part,
        transkripNilai: MultipartBody.Part,
        slipPembayaran: MultipartBody.Part,

    ) {
        viewModelScope.launch {
            repository.uploadFileSkripsi(
                nim,
                krs,
                transkripNilai,
                slipPembayaran,

            )
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }

}