package com.setyo.similartytextapp.ui.penilaian

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.DetPenilaianResponse
import com.setyo.similartytextapp.data.remote.response.DetsemproDataItem
import com.setyo.similartytextapp.data.remote.response.GetDosenResponse
import com.setyo.similartytextapp.data.remote.response.PenilaianDosenResponse
import com.setyo.similartytextapp.data.remote.response.UpdatePenilaianResponse
import com.setyo.similartytextapp.data.remote.response.UpdateUserResponse
import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.data.remote.retrofit.ApiService
import com.setyo.similartytextapp.model.DosenModel
//import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch

class PenilaianViewModel(private val repository: UserRepository) : ViewModel() {
    val penilaianResponse: LiveData<PenilaianDosenResponse> = repository.penilaianResponse
    val detPenilaianResponse: LiveData<DetPenilaianResponse> = repository.detPenilaianResponse
    val updatePenilaianResponse: LiveData<UpdatePenilaianResponse> = repository.updatePenilaianResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getPenilaian(id: String) {
        viewModelScope.launch {
            repository.getDataPenilaian(id)
        }
    }

    fun getDetPenilaian(id: String) {
        viewModelScope.launch {
            repository.getDetPenilaian(id)
        }
    }

    fun getUpdatePenilaain(
        id: String,
        judul: String,
        latarBelakang: String,
        rumusanMasalah: String,
        batasanMasalah: String,
        tujuan: String,
        manfaat: String,
        tinjauanPustaka: String,
        metodologi: String,
        kerangkaPemikiran: String,
        jadwalKegiatan: String,
        riwayatPenelitian: String,
        daftarPustaka: String,
        status: String,
        hasil: String
    ) {
        viewModelScope.launch {
            repository.updateDataPenilaain(
                id, judul, latarBelakang, rumusanMasalah, batasanMasalah, tujuan, manfaat,
                tinjauanPustaka, metodologi, kerangkaPemikiran, jadwalKegiatan, riwayatPenelitian,
                daftarPustaka, status, hasil
            )
        }
    }

    fun getDosen(): LiveData<DosenModel> {
        return repository.getDosen()
    }

    fun getApiService(): ApiService {
        return repository.getApiService()
    }

}