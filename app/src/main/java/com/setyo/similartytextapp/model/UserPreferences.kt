package com.setyo.similartytextapp.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    // Home
    fun getUser(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                id_mhs = preferences[ID_KEY] ?:"",
                nim_mhs = preferences[USERNAME_KEY] ?:"",
                nama_mhs = preferences[NAME_KEY] ?:"",
                token = preferences[TOKEN_KEY] ?:"",
                role = preferences[ROLE_KEY] ?:"",
                isLogin = preferences[STATE_KEY] ?: false
            )
        }
    }

    fun getDosen(): Flow<DosenModel> {
        return dataStore.data.map { preferences ->
            DosenModel(
                id_user = preferences[ID_USER] ?:"",
                username_user = preferences[USERDOSEN_KEY] ?:"",
                id_dosen = preferences[ID_DOSEN] ?:"",
                token = preferences[TOKEN_KEY] ?:"",
                role = preferences[ROLE_KEY] ?:"",
                isLogin = preferences[STATE_KEY] ?: false
            )
        }
    }

    //Login
    suspend fun getLoginUser(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[ID_KEY] = user.id_mhs
            preferences[USERNAME_KEY] = user.nim_mhs
            preferences[NAME_KEY] = user.nama_mhs
            preferences[TOKEN_KEY] = user.token
            preferences[ROLE_KEY] = user.role
            preferences[STATE_KEY] = user.isLogin
        }
    }

    suspend fun getLoginDosen(dosen: DosenModel) {
        dataStore.edit { preferences ->
            preferences[ID_USER] = dosen.id_user
            preferences[USERDOSEN_KEY] = dosen.username_user
            preferences[ID_DOSEN] = dosen.id_dosen
            preferences[TOKEN_KEY] = dosen.token
            preferences[ROLE_KEY] = dosen.role
            preferences[STATE_KEY] = dosen.isLogin
        }
    }

    fun getUserResultSkripsi(): Flow<DafSkripsiModel> {
        return dataStore.data.map { preferences ->
            DafSkripsiModel(
                id_dafskripsi = preferences[ID_DAFSKRIPSI] ?:"",
            )
        }
    }

    suspend fun getResultSkripsi(dafSkripsi: DafSkripsiModel) {
        dataStore.edit { preferences ->
            preferences[ID_DAFSKRIPSI] = dafSkripsi.id_dafskripsi
        }
    }

    suspend fun logoutUser() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun getToken() {
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = true
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val ID_KEY = stringPreferencesKey("id_mhs")
        private val ID_USER = stringPreferencesKey("id_user")
        private val ID_DOSEN = stringPreferencesKey("id_dosen")
        private val USERNAME_KEY = stringPreferencesKey("nim_mhs")
        private val USERDOSEN_KEY = stringPreferencesKey("username_mhs")
        private val NAME_KEY = stringPreferencesKey("nama_mhs")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val ROLE_KEY = stringPreferencesKey("role")
        private val STATE_KEY = booleanPreferencesKey("state")

        private val ID_DAFSKRIPSI = stringPreferencesKey("id_dafskripsi")

        fun getInstance(dataStore: DataStore<Preferences>) : UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}