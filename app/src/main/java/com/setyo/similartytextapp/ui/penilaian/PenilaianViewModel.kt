package com.setyo.similartytextapp.ui.penilaian

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val updatePenilaianResponse: LiveData<UpdatePenilaianResponse> = repository.updatePenilaianResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getPenilaian(id: String) {
        viewModelScope.launch {
            repository.getDataPenilaian(id)
        }
    }

    fun getUpdatePenilaain(id: String, ketrev: String) {
        viewModelScope.launch {
            repository.updateDataPenilaain(id, ketrev)
        }
    }

    fun getDosen(): LiveData<DosenModel> {
        return repository.getDosen()
    }

    fun getApiService(): ApiService {
        return repository.getApiService()
    }

}