package com.setyo.similartytextapp.data.remote.retrofit

import com.setyo.similartytextapp.data.remote.response.*
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

//    @FormUrlEncoded
    @GET("mahasiswarest")
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
    @GET("mahasiswarest/{id}")
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
    @FormUrlEncoded
    @PUT("mahasiswarest/{id_mhs}")
    fun updateDataUser(
        @Path("id_mhs") id: String,
        @Field("nim_mhs") username: String,
        @Field("password_mhs") password: String,
        @Field("nama_mhs") name: String,
        @Field("alamat_mhs") address: String
    ): Call<UpdateUserResponse>
}