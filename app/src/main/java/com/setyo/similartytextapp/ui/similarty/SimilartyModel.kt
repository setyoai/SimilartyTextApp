package com.setyo.similartytextapp.ui.similarty

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SimilartyModel (
    @SerializedName("id_judul")
    val idJudul: Int,

    @SerializedName("judul_skripsi")
    val judulSkripsi: String,

    @SerializedName("tahun_skripsi")
    val tahunSkripsi: String
)
