package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class PenilaianDosenResponse(

	@field:SerializedName("detsempro_data")
	val detsemproData: List<DetsemproDataItem>
)

data class DetsemproDataItem(

	@field:SerializedName("id_detsempro")
	val idDetSempro: String,

	@field:SerializedName("id_sempro")
	val idSempro: String,

	@field:SerializedName("tanggal_sempro")
	val tanggalSempro: String,

	@field:SerializedName("tanggal_dafsempro")
	val tanggalDafsempro: String,

	@field:SerializedName("nim_detsempro")
	val nimDetsempro: String,

	@field:SerializedName("ketrev_detsempro")
	val ketrevDetsempro: String,

	@field:SerializedName("nama_ruangan")
	val namaRuangan: String,

	@field:SerializedName("jam_sempro")
	val jamSempro: String,

	@field:SerializedName("judul_dafsempro")
	val juduldafsempro: String,

	@field:SerializedName("ketua_penguji")
	val ketuaPenguji: String,

	@field:SerializedName("anggota_penguji1")
	val anggotaPenguji1: String,

	@field:SerializedName("anggota_penguji2")
	val anggotaPenguji2: String,

	@field:SerializedName("level_dosen")
	val levelDosen: String,

	@field:SerializedName("hasil_sempro")
	val hasilSempro: String,

	@field:SerializedName("status_dafsempro")
	val statusDafsempro: String,

	@field:SerializedName("nama_detsempro")
	val namaDetsempro: String
)
