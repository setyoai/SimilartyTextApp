package com.setyo.similartytextapp.ui.similarty

import com.google.gson.annotations.SerializedName

data class SimilartyModel(

	@field:SerializedName("tb_judulskripsi")
	val tbJudulskripsi: List<TbJudulskripsiItem>
)

data class TbJudulskripsiItem(

	@field:SerializedName("nama_mhs")
	val namaMhs: String,

	@field:SerializedName("tahun_skripsi")
	val tahunSkripsi: String,

	@field:SerializedName("judul_skripsi")
	val judulSkripsi: String,

	@field:SerializedName("nim_mhs")
	val nimMhs: String,

	@field:SerializedName("id_judul")
	val idJudul: String
)
