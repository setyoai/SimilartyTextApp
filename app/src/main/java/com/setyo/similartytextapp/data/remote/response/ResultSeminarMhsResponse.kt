package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResultSeminarMhsResponse(

	@field:SerializedName("sempro_mhs_data")
	val semproMhsData: SemproMhsData,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class SemproMhsData(

	@field:SerializedName("id_sempro")
	val idSempro: String,

	@field:SerializedName("tanggal_sempro")
	val tanggalSempro: String,

	@field:SerializedName("hasil_sempro")
	val hasilSempro: String,

	@field:SerializedName("tanggal_dafsempro")
	val tanggalDafsempro: String,

	@field:SerializedName("anggota_penguji1")
	val anggotaPenguji1: String,

	@field:SerializedName("anggota_penguji2")
	val anggotaPenguji2: String,

	@field:SerializedName("nim_sempro")
	val nimSempro: String,

	@field:SerializedName("ketua_penguji")
	val ketuaPenguji: String,

	@field:SerializedName("nama_sempro")
	val namaSempro: String,

	@field:SerializedName("status_sempro")
	val statusSempro: String,

	@field:SerializedName("id_detsempro")
	val idDetsempro: String,

	@field:SerializedName("nama_ruangan")
	val namaRuangan: String,

	@field:SerializedName("jam_sempro")
	val jamSempro: String
)
