package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResultDafSkripsiResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("dafskripsi_data")
	val dafskripsiData: DafskripsiData ,
)

data class DafskripsiData(

	@field:SerializedName("id_dafskripsi")
	val idDafskripsi: String? = null,

	@field:SerializedName("status_dafskripsi")
	val statusDafskripsi: String? = null,

	@field:SerializedName("keterangan_dafskripsi")
	val keteranganDafskripsi: String? = null
)
