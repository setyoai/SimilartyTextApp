package com.setyo.similartytextapp.ui.seminar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.DaftarSeminarResponse
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class SeminarViewModel(private val repository: UserRepository): ViewModel() {
    val dafsemResponse: LiveData<DaftarSeminarResponse> = repository.dafsemResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun uploadFile(
        transkripNilai: MultipartBody.Part,
        pengesahan: MultipartBody.Part,
        bukuBimbingan: MultipartBody.Part,
        kwKomputer: MultipartBody.Part,
        kwInggris: MultipartBody.Part,
        kwKewirausahaan: MultipartBody.Part,
        slipPembayaran: MultipartBody.Part,
        plagiasi: MultipartBody.Part,
    ) {
        viewModelScope.launch {
            repository.uploadFile(
                transkripNilai,
                pengesahan,
                bukuBimbingan,
                kwKomputer,
                kwInggris,
                kwKewirausahaan,
                slipPembayaran,
                plagiasi
            )
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }

}