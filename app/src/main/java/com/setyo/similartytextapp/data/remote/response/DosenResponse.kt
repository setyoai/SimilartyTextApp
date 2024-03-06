package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DosenResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("dosen_data")
	val dosenData: DosenData
)

data class DosenData(

	@field:SerializedName("id_dosen")
	val idDosen: String? = null,

	@field:SerializedName("nidn_dosen")
	val nidnDosen: String? = null,

	@field:SerializedName("nama_dosen")
	val namaDosen: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("alamat_dosen")
	val alamatDosen: String? = null,

	@field:SerializedName("email_dosen")
	val emailDosen: String? = null,

	@field:SerializedName("nohp_dosen")
	val nohpDosen: String? = null
)
