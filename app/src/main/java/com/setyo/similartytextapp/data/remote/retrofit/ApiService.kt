package com.setyo.similartytextapp.data.remote.retrofit

import com.setyo.similartytextapp.data.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("registermahasiswa")
    fun registerUser(
        @Field("nim_mhs") username: String,
        @Field("nama_mhs") name: String,
        @Field("password_mhs") password: String,
    ) : Call<RegisterResponse>

//    @FormUrlEncoded
    @GET("loginmahasiswa")
    fun loginUser(
        @Query("nim_mhs") username: String,
        @Query("password_mhs") password: String
    ): Call<LoginResponse>

//    @Multipart
//    @POST("get-disease-name")
//    fun uploadImage(
//        @Header("token") token: String,
//        @Part image: MultipartBody.Part
//    ): Call<PredictionResponse>
//
//    @GET("get-user-history")
//    fun getHistoryUser(
//        @Header("token") token: String,
//    ): Call<List<HistoryResponseItem>>
//
    @GET("mahasiswaapi/{id}")
    fun getUserData(
        @Path("id") id: String,
    ): Call<UserResponse>
//
//    @Multipart
//    @POST("update-user")
//    fun updateUser(
//        @Header("token") token: String,
//        @Part avatar_image: MultipartBody.Part
//    ): Call<UpdateUserResponse>
//
//    @FormUrlEncoded
//    @POST("update-user")
//    fun updateDataUser(
//        @Header("token") token: String,
//        @Field("username") username: String,
//        @Field("password") password: String,
//        @Field("name") name: String
//    ): Call<UpdateUserResponse>
}