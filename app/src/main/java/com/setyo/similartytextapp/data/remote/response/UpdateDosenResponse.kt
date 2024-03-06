package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateDosenResponse(

	@field:SerializedName("dosen_update")
	val dosenUpdate: DosenUpdate,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class DosenUpdate(

	@field:SerializedName("nidn_dosen")
	val nidnDosen: String,

	@field:SerializedName("nama_dosen")
	val namaDosen: String,

	@field:SerializedName("alamat_dosen")
	val alamatDosen: String,

	@field:SerializedName("email_dosen")
	val emailDosen: String,

	@field:SerializedName("nohp_dosen")
	val nohpDosen: String
)
