package com.setyo.similartytextapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.setyo.similartytextapp.di.Injection
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.home.HomeViewModel
import com.setyo.similartytextapp.ui.login.LoginViewModel
import com.setyo.similartytextapp.ui.main.MainViewModel
import com.setyo.similartytextapp.ui.profile.ProfileViewModel
import com.setyo.similartytextapp.ui.register.RegisterViewModel
import com.setyo.similartytextapp.ui.seminar.SeminarViewModel
import com.setyo.similartytextapp.ui.similarty.SimilartyViewModel

class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SeminarViewModel::class.java) -> {
                SeminarViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SimilartyViewModel::class.java) -> {
                SimilartyViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
        }
    }
}