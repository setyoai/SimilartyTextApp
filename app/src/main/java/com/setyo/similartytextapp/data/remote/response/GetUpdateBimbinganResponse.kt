package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetUpdateBimbinganResponse(

	@field:SerializedName("update_bimbingandosen_data")
	val updateBimbingandosenData: List<UpdateBimbingandosenDataItem>
)

data class UpdateBimbingandosenDataItem(

	@field:SerializedName("tanggal_bimbingan")
	val tanggalBimbingan: String,

	@field:SerializedName("id_bimbingan")
	val idBimbingan: String,

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
