package com.setyo.similartytextapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.setyo.similartytextapp.data.remote.response.*
import com.setyo.similartytextapp.data.remote.retrofit.ApiService
import com.setyo.similartytextapp.model.UserModel
import com.setyo.similartytextapp.model.UserPreferences
import com.setyo.similartytextapp.ui.Event
import com.setyo.similartytextapp.data.remote.response.LoginResponse
import com.setyo.similartytextapp.data.remote.response.RegisterResponse
import com.setyo.similartytextapp.data.remote.response.UpdateUserResponse
import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.model.DafSkripsiModel
import com.setyo.similartytextapp.model.DosenModel
import com.setyo.similartytextapp.ui.similarty.SimilartyModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository (
    private val apiService: ApiService,
    private val preferences: UserPreferences
) {

    private val _judulList = MutableLiveData<SimilartyModel>()
    val judulList: LiveData<SimilartyModel>  = _judulList

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _penilaianResponse = MutableLiveData<PenilaianDosenResponse>()
    val penilaianResponse : LiveData<PenilaianDosenResponse> = _penilaianResponse

    private val _detPenilaianResponse = MutableLiveData<DetPenilaianResponse>()
    val detPenilaianResponse : LiveData<DetPenilaianResponse> = _detPenilaianResponse

    private val _dosbingResponse = MutableLiveData<DosbingResponse>()
    val dosbingResponse : LiveData<DosbingResponse> = _dosbingResponse

    private val _dosbingMhsResponse = MutableLiveData<DosbingMhsResponse>()
    val dosbingMhsResponse : LiveData<DosbingMhsResponse> = _dosbingMhsResponse

    private val _createBimbinganResponse = MutableLiveData<CreateBimbinganResponse>()
    val createBimbinganResponse : LiveData<CreateBimbinganResponse> = _createBimbinganResponse

    private val _bimbinganResponse = MutableLiveData<BimbinganResponse>()
    val bimbinganResponse : LiveData<BimbinganResponse> = _bimbinganResponse

    private val _bimbinganDosenResponse = MutableLiveData<BimbinganDosenResponse>()
    val bimbinganDosenResponse : LiveData<BimbinganDosenResponse> = _bimbinganDosenResponse

    private val _getUpdatebimbinganResponse = MutableLiveData<GetUpdateBimbinganResponse>()
    val getUpdatebimbinganResponse : LiveData<GetUpdateBimbinganResponse> = _getUpdatebimbinganResponse

    private val _updatebimbinganResponse = MutableLiveData<UpdateBimbinganResponse>()
    val updatebimbinganResponse : LiveData<UpdateBimbinganResponse> = _updatebimbinganResponse

    private val _updatePenilaianResponse = MutableLiveData<UpdatePenilaianResponse>()
    val updatePenilaianResponse : MutableLiveData<UpdatePenilaianResponse> = _updatePenilaianResponse

    private val _getDosenResponse = MutableLiveData<GetDosenResponse>()
    val getDosenResponse: LiveData<GetDosenResponse> = _getDosenResponse

    private val _loginUserResponse = MutableLiveData<LoginUserResponse>()
    val loginUserResponse: LiveData<LoginUserResponse> = _loginUserResponse

    private val _userResponse = MutableLiveData<UserResponse>()
    val userResponse : LiveData<UserResponse> = _userResponse

    private val _resultDafSkripsiResponse = MutableLiveData<ResultDafSkripsiResponse>()
    val resultDafSkripsiResponse : LiveData<ResultDafSkripsiResponse> = _resultDafSkripsiResponse

    private val _dafsemResponse = MutableLiveData<DaftarSeminarResponse>()
    val dafsemResponse : LiveData<DaftarSeminarResponse> = _dafsemResponse

    private val _dafSemproResponse = MutableLiveData<DafSemproResponse>()
    val dafSemproResponse : LiveData<DafSemproResponse> = _dafSemproResponse

    private val _dafSkripsiResponse = MutableLiveData<DafSkripsiResponse>()
    val dafSkripsiResponse : LiveData<DafSkripsiResponse> = _dafSkripsiResponse

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

    fun loginUserDosen(username: String, password: String) {
        _isLoading.value = true
        val client = apiService.loginUserDosen(username, password)
        client.enqueue(object : Callback<LoginUserResponse> {
            override fun onResponse(call: Call<LoginUserResponse>, response: Response<LoginUserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _textToast.value = Event("Login Berhasil")
                    _loginUserResponse.value = response.body()
                } else {
                    _textToast.value = Event("Username atau password salah!")
                    Log.e(TAG,"onFailure: ${response.message()}, ${response.body()?.message.toString()}")
                }
            }
            override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
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

    fun getDataBimbingan(dosbingid: String, dosenid: String) {
        _isLoading.value = true
        val client = apiService.getDataBimbingan(dosbingid, dosenid)
        client.enqueue(object : Callback<BimbinganResponse> {
            override fun onResponse(
                call: Call<BimbinganResponse>,
                response: Response<BimbinganResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val listBimbingan = response.body()?.bimbinganData
                    _bimbinganResponse.value = listBimbingan?.let {
                        BimbinganResponse(it)
                    }
                }
            }
            override fun onFailure(call: Call<BimbinganResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDataDosenBimbingan(id: String) {
        _isLoading.value = true
        val client = apiService.getDataDosenBimbingan(id)
        client.enqueue(object : Callback<BimbinganDosenResponse> {
            override fun onResponse(
                call: Call<BimbinganDosenResponse>,
                response: Response<BimbinganDosenResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val listDosenBimbingan = response.body()?.bimbinganDosenData
                    _bimbinganDosenResponse.value = listDosenBimbingan?.let {
                        BimbinganDosenResponse(it)
                    }
                }
            }
            override fun onFailure(call: Call<BimbinganDosenResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDataUpdateDosbing(id: String) {
        _isLoading.value = true
        val client = apiService.getDataUpdateDosenBimbingan(id)
        client.enqueue(object : Callback<GetUpdateBimbinganResponse> {
            override fun onResponse(
                call: Call<GetUpdateBimbinganResponse>,
                response: Response<GetUpdateBimbinganResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val listUpdateBimbingan = response.body()?.updateBimbingandosenData
                    _getUpdatebimbinganResponse.value = listUpdateBimbingan?.let {
                        GetUpdateBimbinganResponse(it)
                    }
                }
            }
            override fun onFailure(call: Call<GetUpdateBimbinganResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetPenilaian(id: String) {
        _isLoading.value = true
        val client = apiService.getDataDetPenilaian(id)
        client.enqueue(object : Callback<DetPenilaianResponse> {
            override fun onResponse(
                call: Call<DetPenilaianResponse>,
                response: Response<DetPenilaianResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                        val detPenilaian = response.body()?.detpenilaianData
                        _detPenilaianResponse.value = detPenilaian?.let {
                            DetPenilaianResponse(it)
                    }
                }
            }
            override fun onFailure(call: Call<DetPenilaianResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDataPenilaian(id: String) {
        _isLoading.value = true
        val client = apiService.getDataPenilaian(id)
        client.enqueue(object : Callback<PenilaianDosenResponse> {
            override fun onResponse(
                call: Call<PenilaianDosenResponse>,
                response: Response<PenilaianDosenResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val listPenilaian = response.body()?.detsemproData
                    _penilaianResponse.value = listPenilaian?.let {
                        PenilaianDosenResponse(it)
                    }
                }
            }
            override fun onFailure(call: Call<PenilaianDosenResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDataDosbing(id: String) {
        _isLoading.value = true
        val client = apiService.getDataDosbing(id)
        client.enqueue(object : Callback<DosbingResponse> {
            override fun onResponse(
                call: Call<DosbingResponse>,
                response: Response<DosbingResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val listPenilaian = response.body()?.dosbingData
                    _dosbingResponse.value = listPenilaian?.let {
                        DosbingResponse(it)
                    }
                }
            }
            override fun onFailure(call: Call<DosbingResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDataMhsDosbing(id: String) {
        _isLoading.value = true
        val client = apiService.getDataMhsDosbing(id)
        client.enqueue(object : Callback<DosbingMhsResponse> {
            override fun onResponse(
                call: Call<DosbingMhsResponse>,
                response: Response<DosbingMhsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dosbingMhsResponse.value = response.body()
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DosbingMhsResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getUserDosen(id: String) {
        _isLoading.value = true
        val client = apiService.getUserDosen(id)
        client.enqueue(object : Callback<GetDosenResponse> {
            override fun onResponse(
                call: Call<GetDosenResponse>,
                response: Response<GetDosenResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _getDosenResponse.value = response.body()
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<GetDosenResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getUserDafSkripsi(id: String) {
        _isLoading.value = true
        val client = apiService.getUserDafSkripsi(id)
        client.enqueue(object : Callback<ResultDafSkripsiResponse> {
            override fun onResponse(
                call: Call<ResultDafSkripsiResponse>,
                response: Response<ResultDafSkripsiResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _resultDafSkripsiResponse.value = response.body()
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResultDafSkripsiResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun uploadFileSkripsi(
        nim: RequestBody,
        krs: MultipartBody.Part,
        transkripNilai: MultipartBody.Part,
        slipPembayaran: MultipartBody.Part
    ) {
        _isLoading.value = true
        val client = apiService.uploadFileSkripsi(
            nim,
            krs,
            transkripNilai,
            slipPembayaran,
        )
        client.enqueue(object : Callback<DafSkripsiResponse> {
            override fun onResponse(call: Call<DafSkripsiResponse>, response: Response<DafSkripsiResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _textToast.value = Event("Berhasil Upload File")
                    _dafSkripsiResponse.value = response.body()
                } else {
                    _textToast.value = Event("Gagal Upload File")
                    Log.e(this@UserRepository.javaClass.simpleName,"onFailure: ${response.message()}, ${response.body()?.message.toString()}")
                }
            }
            override fun onFailure(call: Call<DafSkripsiResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Bisa dicoba kembali")
                Log.e(this@UserRepository.javaClass.simpleName, "onFailure: ${t.message.toString()}")
            }
        })
    }


    fun getDafSeminar(id: String) {
        _isLoading.value = true
        val client = apiService.getDafSempro(id)
        client.enqueue(object : Callback<DafSemproResponse> {
            override fun onResponse(
                call: Call<DafSemproResponse>,
                response: Response<DafSemproResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dafSemproResponse.value = response.body()
                } else {
                    Log.e(TAG,"onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DafSemproResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Tidak Terhubung ke Internet")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun uploadFileSeminar(
        id: RequestBody,
        judul: RequestBody,
        transkripNilai: MultipartBody.Part,
        pengesahan: MultipartBody.Part,
//        bukuBimbingan: MultipartBody.Part,
//        kwKomputer: MultipartBody.Part,
//        kwInggris: MultipartBody.Part,
//        kwKewirausahaan: MultipartBody.Part,
//        slipPembayaran: MultipartBody.Part,
//        plagiasi: MultipartBody.Part,
    ) {
        _isLoading.value = true
        val client = apiService.uploadFile(
            id,
            judul,
            transkripNilai,
            pengesahan,
//            bukuBimbingan,
//            kwKomputer,
//            kwInggris,
//            kwKewirausahaan,
//            slipPembayaran,
//            plagiasi
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

    fun bimbinganMhs(
        dosbingid: RequestBody,
        mhsnim: RequestBody,
//        bab: RequestBody,
        ket: RequestBody,
//        file: MultipartBody.Part,
        dosenid: RequestBody
    ) {
        _isLoading.value = true
        val client = apiService.bimbinganMhs( dosbingid, mhsnim, ket, dosenid)
        client.enqueue(object : Callback<CreateBimbinganResponse> {
            override fun onResponse(call: Call<CreateBimbinganResponse>, response: Response<CreateBimbinganResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _textToast.value = Event("Berhasil Mengirim")
                    _createBimbinganResponse.value = response.body()
                } else {
                    _textToast.value = Event("Gagal Mengirim")
                    Log.e(this@UserRepository.javaClass.simpleName,"onFailure: ${response.message()}, ${response.body()?.message.toString()}")
                }
            }
            override fun onFailure(call: Call<CreateBimbinganResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Bisa dicoba kembali")
                Log.e(this@UserRepository.javaClass.simpleName, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getTitleData() {
        _isLoading.value = true
        val client = apiService.getTitledata()
        client.enqueue(object : Callback<SimilartyModel> {
            override fun onResponse(
                call: Call<SimilartyModel>,
                response: Response<SimilartyModel>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val listData = response.body()?.tbJudulskripsi
                    _judulList.value = listData?.let { SimilartyModel(it) }
                }
            }

            override fun onFailure(call: Call<SimilartyModel>, t: Throwable) {
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
    fun updateDataUser(
        id: String, username: String, password: String, name: String, address: String, nohp: String,
        email: String
    ) {
        _isLoading.value = true
        val client = apiService.updateDataUser(id, username, password, name, address, nohp, email)
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
                Log.e(TAG, "onFailure: ${t.message.toString()}")  }
        })
    }

    fun updateDataBimbingan(id: String, balasanket: String) {
        _isLoading.value = true
        val client = apiService.updateDataBimbingan(id, balasanket)
        client.enqueue(object : Callback<UpdateBimbinganResponse> {
            override fun onResponse(call: Call<UpdateBimbinganResponse>, response: Response<UpdateBimbinganResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _textToast.value = Event("Berhasil Kirim Balasan Bimbingan")
                    _updatebimbinganResponse.value = response.body()
                } else {
                    _textToast.value = Event("Gagal Kirim Balasan Bimbingan")
                    Log.e(TAG,"onFailure: ${response.message()}, ${response.body()?.message.toString()}")
                }
            }
            override fun onFailure(call: Call<UpdateBimbinganResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun updateDataPenilaain(
        id: String,
        judul: String,
        latarBelakang: String,
        rumusanMasalah: String,
        batasanMasalah: String,
        tujuan: String,
        manfaat: String,
        tinjauanPustaka: String,
        metodologi: String,
        kerangkaPemikiran: String,
        jadwalKegiatan: String,
        riwayatPenelitian: String,
        daftarPustaka: String,
        status: String,
        hasil: String
    ) {
        _isLoading.value = true
        val client = apiService.updateDataPenilaian(
            id, judul, latarBelakang, rumusanMasalah, batasanMasalah, tujuan, manfaat,
            tinjauanPustaka, metodologi, kerangkaPemikiran, jadwalKegiatan, riwayatPenelitian,
            daftarPustaka, status, hasil
        )
        client.enqueue(object : Callback<UpdatePenilaianResponse> {
            override fun onResponse(call: Call<UpdatePenilaianResponse>, response: Response<UpdatePenilaianResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _textToast.value = Event("Berhasil Update Penilaian")
                    _updatePenilaianResponse.value = response.body()
                } else {
                    _textToast.value = Event("Gagal Update Penilaian")
                    Log.e(TAG,"onFailure: ${response.message()}, ${response.body()?.message.toString()}")
                }
            }
            override fun onFailure(call: Call<UpdatePenilaianResponse>, t: Throwable) {
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

    fun getDosen(): LiveData<DosenModel> {
        return preferences.getDosen().asLiveData()
    }

    fun getUserResultSkripsi(): LiveData<DafSkripsiModel> {
        return preferences.getUserResultSkripsi().asLiveData()
    }

    suspend fun getLoginUser(user: UserModel) {
        preferences.getLoginUser(user)
    }

    suspend fun getLoginDosen(dosen: DosenModel) {
        preferences.getLoginDosen(dosen)
    }

    suspend fun getResultSkripsi(dafSkripsi: DafSkripsiModel) {
        preferences.getResultSkripsi(dafSkripsi)
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