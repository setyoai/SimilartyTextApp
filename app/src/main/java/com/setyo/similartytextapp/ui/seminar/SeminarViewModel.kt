package com.setyo.similartytextapp.ui.seminar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.DaftarSeminarResponse
import com.setyo.similartytextapp.data.remote.response.ResultDafSkripsiResponse
import com.setyo.similartytextapp.model.DafSkripsiModel
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class SeminarViewModel(private val repository: UserRepository): ViewModel() {
    val dafsemResponse: LiveData<DaftarSeminarResponse> = repository.dafsemResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast
    val resultDafSkripsiResponse: LiveData<ResultDafSkripsiResponse> = repository.resultDafSkripsiResponse

    fun uploadFileSeminar(
        id: RequestBody,
        judul: RequestBody,
        transkripNilai: MultipartBody.Part,
        pengesahan: MultipartBody.Part,
//        bukuBimbingan: MultipartBody.Part,
//        kwKomputer: MultipartBody.Part,
//        kwInggris: MultipartBody.Part,
//        kwKewirausahaan: MultipartBody.Part,
//        slipPembayaran: MultipartBody.Part,
//        plagiasi: MultipartBody.Part,
    ) {
        viewModelScope.launch {
            repository.uploadFileSeminar(
                id,
                judul,
                transkripNilai,
                pengesahan,
//                bukuBimbingan,
//                kwKomputer,
//                kwInggris,
//                kwKewirausahaan,
//                slipPembayaran,
//                plagiasi
            )
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }

    fun getDafSkripsi(id: String) {
        viewModelScope.launch {
            repository.getUserDafSkripsi(id)
        }
    }


}