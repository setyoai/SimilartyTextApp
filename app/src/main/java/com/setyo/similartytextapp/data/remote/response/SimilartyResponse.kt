package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class SimilartyResponse(

	@field:SerializedName("top_three_titles")
	val topThreeTitles: List<TopThreeTitlesItem>
)

data class TopThreeTitlesItem(

	@field:SerializedName("nama_mhs")
	val namaMhs: String,

	@field:SerializedName("nomor_urut")
	val nomerUrut: String,

	@field:SerializedName("dosen1_dosbing")
	val dosen1Dosbing: String,

	@field:SerializedName("dosen2_dosbing")
	val dosen2Dosbing: String,

	@field:SerializedName("tahun_skripsi")
	val tahunSkripsi: String,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("persentase_kesamaan")
	val persentaseKesamaan: String,

	@field:SerializedName("nim_mhs")
	val nimMhs: String
)
