package com.setyo.similartytextapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.DosbingResponse
import com.setyo.similartytextapp.data.remote.response.PenilaianDosenResponse
import com.setyo.similartytextapp.model.DosenModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch

class DetailSeminarViewModel(private val repository: UserRepository) : ViewModel() {

    val penilaianResponse: LiveData<PenilaianDosenResponse> = repository.penilaianResponse
    val dosbingResponse: LiveData<DosbingResponse> = repository.dosbingResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getDosbing(id: String) {
        viewModelScope.launch {
            repository.getDataDosbing(id)
        }
    }

    fun getPenilaian(id: String) {
        viewModelScope.launch {
            repository.getDataPenilaian(id)
        }
    }

    fun getDosen(): LiveData<DosenModel> {
        return repository.getDosen()
    }

}