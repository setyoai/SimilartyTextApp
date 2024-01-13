package com.setyo.similartytextapp.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.setyo.similartytextapp.data.remote.response.*
import com.setyo.similartytextapp.data.remote.retrofit.ApiService
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.model.UserPreferences
import com.setyo.similartytextapp.ui.Event
import com.setyo.similartytextapp.ui.similarty.SimilartyModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Part

class UserRepository (
    private val apiService: ApiService,
    private val preferences: UserPreferences
) {

    private val _judulList = MutableLiveData<List<SimilartyModel>>()
    val judulList: LiveData<List<SimilartyModel>> get() = _judulList

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _userResponse = MutableLiveData<UserResponse>()
    val userResponse : LiveData<UserResponse> = _userResponse

    private val _dafsemResponse = MutableLiveData<DaftarSeminarResponse>()
    val dafsemResponse : LiveData<DaftarSeminarResponse> = _dafsemResponse

    private val _updateUserResponse = MutableLiveData<UpdateUserResponse>()
    val updateUserResponse : LiveData<UpdateUserResponse> = _updateUserResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _textToast = MutableLiveData<Event<String>>()
    val textToast: LiveData<Event<String>> = _textToast

    fun registerUser(username: String, password: String, name: String) {
        _isLoading.value = true
        val client = apiService.registerUser(username, password, name)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _textToast.value = Event("Selamat Anda Berhasil Registrasi")
                    _registerResponse.value = response.body()
                } else {
                    _textToast.value = Event("Akun Sudah Pernah Dibuat")
                    Log.e(TAG,"onFailure: ${response.message()}, ${response.body()?.message.toString()}")
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun loginUser(username: String, password: String) {
        _isLoading.value = true
        val client = apiService.loginUser(username, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _textToast.value = Event("Login Berhasil")
                    _loginResponse.value = response.body()
                } else {
                    _textToast.value = Event("Username atau password salah!")
                    Log.e(TAG,"onFailure: ${response.message()}, ${response.body()?.message.toString()}")
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getUserData(id: String) {
        _isLoading.value = true
        val client = apiService.getUserData(id)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _userResponse.value = response.body()
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun uploadFile(
        transkripNilai: MultipartBody.Part,
        pengesahan: MultipartBody.Part,
        bukuBimbingan: MultipartBody.Part,
        kwKomputer: MultipartBody.Part,
        kwInggris: MultipartBody.Part,
        kwKewirausahaan: MultipartBody.Part,
        slipPembayaran: MultipartBody.Part,
        plagiasi: MultipartBody.Part,
    ) {
        _isLoading.value = true
        val client = apiService.uploadFile(
            transkripNilai,
            pengesahan,
            bukuBimbingan,
            kwKomputer,
            kwInggris,
            kwKewirausahaan,
            slipPembayaran,
            plagiasi
        )
        client.enqueue(object : Callback<DaftarSeminarResponse> {
            override fun onResponse(call: Call<DaftarSeminarResponse>, response: Response<DaftarSeminarResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _textToast.value = Event("Berhasil Upload File")
                    _dafsemResponse.value = response.body()
                } else {
                    _textToast.value = Event("Gagal Upload File")
                    Log.e(this@UserRepository.javaClass.simpleName,"onFailure: ${response.message()}, ${response.body()?.message.toString()}")
                }
            }
            override fun onFailure(call: Call<DaftarSeminarResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Bisa dicoba kembali")
                Log.e(this@UserRepository.javaClass.simpleName, "onFailure: ${t.message.toString()}")
            }
        })
    }

//    fun getTitleData(id: String) {
//        _isLoading.value = true
//        val client = apiService.getUserData(id)
//        client.enqueue(object : Callback<UserResponse> {
//            override fun onResponse(
//                call: Call<UserResponse>,
//                response: Response<UserResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _userResponse.value = response.body()
//                } else {
//                    Log.e(TAG,"onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                _isLoading.value = false
//                _textToast.value = Event("Tidak Terhubung ke Internet")
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//    }


    fun getTitleData() {
        _isLoading.value = true
        val client = apiService.getTitledata()
        client.enqueue(object : Callback<List<SimilartyModel>> {
            override fun onResponse(
                call: Call<List<SimilartyModel>>,
                response: Response<List<SimilartyModel>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _judulList.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<SimilartyModel>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }



//    fun updateUser(token: String, avatar_image:MultipartBody.Part) {
//        _isLoading.value = true
//        val client = apiService.updateUser(token, avatar_image)
//        client.enqueue(object : Callback<UpdateUserResponse> {
//            override fun onResponse(call: Call<UpdateUserResponse>, response: Response<UpdateUserResponse>) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _textToast.value = Event("Berhasil Update Profile")
//                    _updateUserResponse.value = response.body()
//                } else {
//                    _textToast.value = Event("Gagal Update Profile")
//                    Log.e(TAG,"onFailure: ${response.message()}, ${response.body()?.message.toString()}")
//                }
//            }
//            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
//                _isLoading.value = false
//                _textToast.value = Event("Bisa dicoba kembali")
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//    }
//
    fun updateDataUser(id: String, username: String, password: String, name: String, address: String) {
        _isLoading.value = true
        val client = apiService.updateDataUser(id, username, password, name, address)
        client.enqueue(object : Callback<UpdateUserResponse> {
            override fun onResponse(call: Call<UpdateUserResponse>, response: Response<UpdateUserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _textToast.value = Event("Berhasil Update Profile")
                    _updateUserResponse.value = response.body()
                } else {
                    _textToast.value = Event("Gagal Update Profile")
                    Log.e(TAG,"onFailure: ${response.message()}, ${response.body()?.message.toString()}")
                }
            }
            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getApiService(): ApiService {
        return apiService
    }

    fun getUser(): LiveData<UserModel> {
        return preferences.getUser().asLiveData()
    }

    suspend fun getLoginUser(user: UserModel) {
        preferences.getLoginUser(user)
    }

    suspend fun getToken() {
        preferences.getToken()
    }

    suspend fun logoutUser() {
        preferences.logoutUser()
    }

    companion object {
        private const val TAG = "Bearer"

        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(preferences: UserPreferences, apiService: ApiService): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, preferences)
            }.also { instance = it }
    }
}