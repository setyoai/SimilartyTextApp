package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DafSemproResponse(

	@field:SerializedName("dafsempro_data")
	val dafsemproData: DafsemproData,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class DafsemproData(

	@field:SerializedName("transkrip_dafsempro")
	val transkripDafsempro: String,

	@field:SerializedName("slippembayaran_dafsempro")
	val slippembayaranDafsempro: String,

	@field:SerializedName("keterangan_dafsempro")
	val keteranganDafsempro: String,

	@field:SerializedName("id_dafsempro")
	val idDafsempro: String,

	@field:SerializedName("status_dafsempro")
	val statusDafsempro: String
)
