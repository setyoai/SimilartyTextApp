package com.setyo.similartytextapp.ui.home.dosbing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.BimbinganDosenResponse
import com.setyo.similartytextapp.data.remote.response.DosbingResponse
import com.setyo.similartytextapp.data.remote.response.GetUpdateBimbinganResponse
import com.setyo.similartytextapp.data.remote.response.PenilaianDosenResponse
import com.setyo.similartytextapp.data.remote.response.UpdateBimbinganResponse
import com.setyo.similartytextapp.model.DosenModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class DosbingViewModel(private val repository: UserRepository) : ViewModel() {

    val dosbingResponse: LiveData<DosbingResponse> = repository.dosbingResponse
    val bimbinganDosenResponse: LiveData<BimbinganDosenResponse> = repository.bimbinganDosenResponse
    val getUpdatebimbinganResponse: LiveData<GetUpdateBimbinganResponse> = repository.getUpdatebimbinganResponse
    val updatebimbinganResponse: LiveData<UpdateBimbinganResponse> = repository.updatebimbinganResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getDosbing(id: String) {
        viewModelScope.launch {
            repository.getDataDosbing(id)
        }
    }

    fun getDataDosenBimbingan(id: String) {
        viewModelScope.launch {
            repository.getDataDosenBimbingan(id)
        }
    }

    fun getDataUpdateDosbing(id: String) {
        viewModelScope.launch {
            repository.getDataUpdateDosbing(id)
        }
    }

    fun updateDataBimbingan(id: String, balasanket: String) {
        viewModelScope.launch {
            repository.updateDataBimbingan(id, balasanket)
        }
    }

    fun getDosen(): LiveData<DosenModel> {
        return repository.getDosen()
    }

}