package com.setyo.similartytextapp.ui.home.hasilseminarmahasiswa

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.DafSemproResponse
import com.setyo.similartytextapp.data.remote.response.ResultDafSkripsiResponse
import com.setyo.similartytextapp.data.remote.response.ResultSeminarMhsResponse
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch

class HasilSeminarViewModel(private val repository: UserRepository) : ViewModel() {

    val resultSeminarMhsResponse: LiveData<ResultSeminarMhsResponse> = repository.resultSeminarMhsResponse
    val resultDafSkripsiResponse: LiveData<ResultDafSkripsiResponse> = repository.resultDafSkripsiResponse
    val dafsemResponse: LiveData<DafSemproResponse> = repository.dafSemproResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getResultSeminarMhs(id: String) {
        viewModelScope.launch {
            repository.getResultSeminarMhs(id)
        }
    }

    fun getDafSkripsi(id: String) {
        viewModelScope.launch {
            repository.getUserDafSkripsi(id)
        }
    }

    fun getDafSempro(id: String) {
        viewModelScope.launch {
            repository.getDafSeminar(id)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }

}