package com.setyo.similartytextapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.setyo.similartytextapp.model.DosenModel
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event

class MainViewModel(private val repository: UserRepository): ViewModel() {
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }

    fun getDosen(): LiveData<DosenModel> {
        return repository.getDosen()
    }
}