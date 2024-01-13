package com.setyo.similartytextapp.ui.similarty

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import kotlinx.coroutines.launch

class SimilartyViewModel(private val repository: UserRepository) : ViewModel()  {
    val judulList: LiveData<List<SimilartyModel>> = repository.judulList
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getTitle() {
        viewModelScope.launch {
            repository.getTitleData()
        }
    }
}