package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreateBimbinganResponse(

	@field:SerializedName("bimbingan_result")
	val bimbinganResult: BimbinganResult,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class BimbinganResult(

	@field:SerializedName("tanggal_bimbingan")
	val tanggalBimbingan: String,

	@field:SerializedName("ket_bimbingan")
	val ketBimbingan: String
)
