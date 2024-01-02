package com.setyo.similartytextapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.setyo.similartytextapp.data.remote.retrofit.ApiConfig
import com.setyo.similartytextapp.model.UserPreferences
import com.setyo.similartytextapp.repository.UserRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("token")

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = UserPreferences.getInstance(context.dataStore)
        val preferences = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService, preferences)
    }
}