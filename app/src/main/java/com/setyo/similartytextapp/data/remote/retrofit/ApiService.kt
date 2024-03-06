package com.setyo.similartytextapp.data.remote.retrofit

import com.setyo.similartytextapp.data.remote.response.*
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
        @Part("judul_dafsempro") judul: RequestBody,
        @Part transkripNilai: MultipartBody.Part,
        @Part pengesahan: MultipartBody.Part,
        @Part bukuBimbingan: MultipartBody.Part,
        @Part kwKomputer: MultipartBody.Part,
        @Part kwInggris: MultipartBody.Part,
        @Part kwKewirausahaan: MultipartBody.Part,
        @Part slipPembayaran: MultipartBody.Part,
        @Part plagiasi: MultipartBody.Part,
    ): Call<DaftarSeminarResponse>

    @Multipart
    @POST("bimbinganrest")
    fun bimbinganMhs(
        @Part("dosbingid_bimbingan") dosbingid: RequestBody,
        @Part("mhsnim_bimbingan") mhsnim: RequestBody,
//        @Part("bab_bimbingan") bab: RequestBody,
        @Part("ket_bimbingan") ket: RequestBody,
//        @Part file: MultipartBody.Part,
        @Part("dosenid_bimbingan") dosenid: RequestBody,
    ): Call<CreateBimbinganResponse>

    @GET("mahasiswarest/{id}")
    fun getUserData(
        @Path("id") id: String,
    ): Call<UserResponse>

    @GET("dosenrest/{id}")
    fun getDosen(
        @Path("id") id: String,
    ): Call<DosenResponse>

    @GET("detsemprorest/{id}")
    fun getDataPenilaian(
        @Path("id") id: String,
    ): Call<PenilaianDosenResponse>

    @GET("penilaianrest/{id}")
    fun getDataDetPenilaian(
        @Path("id") id: String,
    ): Call<DetPenilaianResponse>

    @GET("mahasiswasemprorest/show/{id_dafsempro}")
    fun getResultSeminarMhs(
        @Path("id_dafsempro") id: String,
    ): Call<ResultSeminarMhsResponse>

    @GET("bimbinganrest/show/{dosbingid_bimbingan}/{dosenid_bimbingan}")
    fun getDataBimbingan(
        @Path("dosbingid_bimbingan") dosbingid: String,
        @Path("dosenid_bimbingan") dosenid: String,
    ): Call<BimbinganResponse>

    @GET("bimbingandosenrest/{id}")
    fun getDataDosenBimbingan(
        @Path("id") id: String,
    ): Call<BimbinganDosenResponse>

    @GET("updatebimbingandosenrest/{id}")
    fun getDataUpdateDosenBimbingan(
        @Path("id") id: String,
    ): Call<GetUpdateBimbinganResponse>

    @GET("dosbingrest/{id}")
    fun getDataDosbing(
        @Path("id") id: String,
    ): Call<DosbingResponse>

    @GET("dosbingapi/{id}")
    fun getDataMhsDosbing(
        @Path("id") id: String,
    ): Call<DosbingMhsResponse>

    @GET("userrest/{id}")
    fun getUserDosen(
        @Path("id") id: String,
    ): Call<GetDosenResponse>

    @GET("dafskripsirest/{id}")
    fun getUserDafSkripsi(
        @Path("id") id: String,
    ): Call<ResultDafSkripsiResponse>

    @GET("dafsemprorest/{id}")
    fun getDafSempro(
        @Path("id") id: String,
    ): Call<DafSemproResponse>

    @GET("judulrest")
    fun getSimilartydata(
        @Query("judul_skripsi") judul: String,
        @Query("id_user") id_user: String,
    ) : Call<SimilartyResponse>
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

    @FormUrlEncoded
    @PUT("dosenrest/{id_mhs}")
    fun updateDosen(
        @Path("id_dosen") id: String,
        @Field("nidn_dosen") username: String,
        @Field("password_dosen") password: String,
        @Field("nama_dosen") name: String,
        @Field("alamat_dosen") address: String,
        @Field("nohp_dosen") nohp: String,
        @Field("email_dosen") email: String,
    ): Call<UpdateDosenResponse>

    @FormUrlEncoded
    @PUT("detsemprorest/{id_detsempro}")
    fun updateDataPenilaian(
        @Path("id_detsempro") id: String,
        @Field("judul") judul: String,
        @Field("latar_belakang") latarBelakang: String,
        @Field("rumusan_masalah") rumusanMasalah: String,
        @Field("batasan_masalah") batasanMasalah: String,
        @Field("tujuan") tujuan: String,
        @Field("manfaat") manfaat: String,
        @Field("tinjauan-pustaka") tinjauanPustaka: String,
        @Field("metodologi") metodologi: String,
        @Field("kerangka_pemikiran") kerangkaPemikiran: String,
        @Field("jadwal_kegiatan") jadwalKegiatan: String,
        @Field("riwayat_penilitian") riwayatPenelitian: String,
        @Field("daftar_pustaka") daftarPustaka: String,
        @Field("status_sempro") status: String,
        @Field("hasil_sempro") hasil: String,
    ): Call<UpdatePenilaianResponse>

    @FormUrlEncoded
    @PUT("bimbinganrest/{id_bimbingan}")
    fun updateDataBimbingan(
        @Path("id_bimbingan") id: String,
        @Field ("balasanket_bimbingan") balasanket: String,
    ): Call<UpdateBimbinganResponse>

    @GET("userrest")
    fun loginUserDosen(
        @Query("username_user") username: String,
        @Query("password_user") password: String
    ): Call<LoginUserResponse>
}