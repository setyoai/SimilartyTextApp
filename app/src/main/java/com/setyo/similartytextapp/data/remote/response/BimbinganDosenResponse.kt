package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class BimbinganDosenResponse(

	@field:SerializedName("bimbingandosen_data")
	val bimbinganDosenData: List<BimbinganDosenDataItem>
)

data class BimbinganDosenDataItem(

	@field:SerializedName("id_bimbingan")
	val idbimbingan: String,

	@field:SerializedName("tanggal_bimbingan")
	val tanggalBimbingan: String,

	@field:SerializedName("balasantanggal_bimbingan")
	val balasantanggalBimbingan: String,

	@field:SerializedName("nama_bimbingan")
	val namaBimbingan: String,

	@field:SerializedName("mhsnim_bimbingan")
	val mhsnimBimbingan: String,

	@field:SerializedName("ket_bimbingan")
	val ketBimbingan: String,

	@field:SerializedName("balasanket_bimbingan")
	val balasanketBimbingan: String
)
