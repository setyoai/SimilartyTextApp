package com.setyo.similartytextapp.ui.similarity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.data.remote.response.SimilartyResponse
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch

class SimilarityViewModel(private val repository: UserRepository) : ViewModel()  {
    val similartyResponse: LiveData<SimilartyResponse> = repository.similartyResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getSimilarty(judul: String, id_user: String) {
        viewModelScope.launch {
            repository.getSimilartyData(judul, id_user)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }
}