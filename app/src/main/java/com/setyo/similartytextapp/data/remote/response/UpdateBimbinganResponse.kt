package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateBimbinganResponse(

	@field:SerializedName("update_bimbingan")
	val updateBimbingan: UpdateBimbingan,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class UpdateBimbingan(

	@field:SerializedName("balasantanggal_bimbingan")
	val balasantanggalBimbingan: String,

	@field:SerializedName("balasanket_bimbingan")
	val balasanketBimbingan: String
)
