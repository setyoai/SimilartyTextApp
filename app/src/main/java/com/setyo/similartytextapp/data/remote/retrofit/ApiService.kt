package com.setyo.similartytextapp.data.remote.retrofit

import com.setyo.similartytextapp.data.remote.response.*
import com.setyo.similartytextapp.ui.similarty.SimilartyModel
import com.setyo.similartytextapp.data.remote.response.LoginResponse
import com.setyo.similartytextapp.data.remote.response.RegisterResponse
import com.setyo.similartytextapp.data.remote.response.UpdateUserResponse
import com.setyo.similartytextapp.data.remote.response.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("mahasiswarest")
    fun registerUser(
        @Field("nim_mhs") username: String,
        @Field("password_mhs") password: String,
        @Field("nama_mhs") name: String,
    ) : Call<RegisterResponse>

    @GET("mahasiswarest")
    fun loginUser(
        @Query("nim_mhs") username: String,
        @Query("password_mhs") password: String
    ): Call<LoginResponse>

    @Multipart
    @POST("dafskripsirest")
    fun uploadFileSkripsi(
        @Part("nim_dafskripsi") nim: RequestBody,
        @Part krs: MultipartBody.Part,
        @Part transkripNilai: MultipartBody.Part,
        @Part slipPembayaran: MultipartBody.Part,
    ): Call<DafSkripsiResponse>

    @Multipart
    @POST("dafsemprorest")
    fun uploadFile(
        @Part("id_dafskripsi") id: RequestBody,
        @Part transkripNilai: MultipartBody.Part,
        @Part pengesahan: MultipartBody.Part,
//        @Part bukuBimbingan: MultipartBody.Part,
//        @Part kwKomputer: MultipartBody.Part,
//        @Part kwInggris: MultipartBody.Part,
//        @Part kwKewirausahaan: MultipartBody.Part,
//        @Part slipPembayaran: MultipartBody.Part,
//        @Part plagiasi: MultipartBody.Part,
    ): Call<DaftarSeminarResponse>

    @GET("mahasiswarest/{id}")
    fun getUserData(
        @Path("id") id: String,
    ): Call<UserResponse>

    @GET("dafskripsirest/{id}")
    fun getUserDafSkripsi(
        @Path("id") id: String,
    ): Call<ResultDafSkripsiResponse>

    @GET("judulrest")
    fun getTitledata() : Call<List<SimilartyModel>>
//
//    @Multipart
//    @POST("update-user")
//    fun updateUser(
//        @Header("token") token: String,
//        @Part avatar_image: MultipartBody.Part
//    ): Call<UpdateUserResponse>
//
    @FormUrlEncoded
    @PUT("mahasiswarest/{id_mhs}")
    fun updateDataUser(
        @Path("id_mhs") id: String,
        @Field("nim_mhs") username: String,
        @Field("password_mhs") password: String,
        @Field("nama_mhs") name: String,
        @Field("alamat_mhs") address: String,
        @Field("nohp_mhs") nohp: String,
        @Field("email_mhs") email: String,
    ): Call<UpdateUserResponse>

    @GET("userrest")
    fun loginUserDosen(
        @Query("username_user") username: String,
        @Query("password_user") password: String
    ): Call<LoginUserResponse>
}