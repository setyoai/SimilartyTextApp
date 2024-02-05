package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DosbingResponse(

	@field:SerializedName("dosbing_data")
	val dosbingData: List<DosbingDataItem>
)

data class DosbingDataItem(

	@field:SerializedName("nim_dosbing")
	val nimDosbing: String,

	@field:SerializedName("nama_dosbing")
	val namaDosbing: String,

	@field:SerializedName("level_dosen")
	val levelDosen: String,

	@field:SerializedName("status_dafsempro")
	val statusDafsempro: String,

	@field:SerializedName("tanggal_dafsempro")
	val tanggalDafsempro: String,

	@field:SerializedName("tanggal_sempro")
	val tanggalSempro: String,

	@field:SerializedName("jam_sempro")
	val jamSempro: String,

	@field:SerializedName("hasil_sempro")
	val hasilSempro: String,

	@field:SerializedName("nama_ruangan")
	val namaRuangan: String,
)
