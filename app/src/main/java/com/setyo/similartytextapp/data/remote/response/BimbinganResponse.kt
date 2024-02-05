package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class BimbinganResponse(

	@field:SerializedName("bimbingan_data")
	val bimbinganData: List<BimbinganDataItem?>? = null
)

data class BimbinganDataItem(

	@field:SerializedName("tanggal_bimbingan")
	val tanggalBimbingan: String? = null,

	@field:SerializedName("balasantanggal_bimbingan")
	val balasantanggalBimbingan: String? = null,

	@field:SerializedName("ket_bimbingan")
	val ketBimbingan: String? = null,

	@field:SerializedName("balasanket_bimbingan")
	val balasanketBimbingan: String? = null
)
