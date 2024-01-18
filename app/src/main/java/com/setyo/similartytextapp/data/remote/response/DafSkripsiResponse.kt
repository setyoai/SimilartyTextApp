package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DafSkripsiResponse(

	@field:SerializedName("upload_result")
	val uploadResult: UploadResultDaf? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class UploadResultDaf(

	@field:SerializedName("slippembayaran_dafskripsi")
	val slippembayaranDafskripsi: String? = null,

	@field:SerializedName("krs_dafskripsi")
	val krsDafskripsi: String? = null,

	@field:SerializedName("transkrip_dafskripsi")
	val transkripDafskripsi: String? = null
)
