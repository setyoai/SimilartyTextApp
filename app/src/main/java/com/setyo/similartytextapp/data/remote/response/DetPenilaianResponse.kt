package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetPenilaianResponse(

	@field:SerializedName("detpenilaian_data")
	val detpenilaianData: List<DetpenilaianDataItem>
)

data class DetpenilaianDataItem(

	@field:SerializedName("manfaat")
	val manfaat: String,

	@field:SerializedName("rumusan_masalah")
	val rumusanMasalah: String,

	@field:SerializedName("latar_belakang")
	val latarBelakang: String,

	@field:SerializedName("tinjauan pustaka")
	val tinjauanPustaka: String,

	@field:SerializedName("hasil_sempro")
	val hasilSempro: String,

	@field:SerializedName("riwayat_penilitian")
	val riwayatPenilitian: String,

	@field:SerializedName("batasan_masalah")
	val batasanMasalah: String,

	@field:SerializedName("tujuan")
	val tujuan: String,

	@field:SerializedName("nama_detsempro")
	val namaDetsempro: String,

	@field:SerializedName("nama_dosen")
	val namaDosen: String,

	@field:SerializedName("nim_detsempro")
	val nimDetsempro: String,

	@field:SerializedName("metodologi")
	val metodologi: String,

	@field:SerializedName("daftar_pustaka")
	val daftarPustaka: String,

	@field:SerializedName("status_sempro")
	val statusSempro: String,

	@field:SerializedName("kerangka_pemikiran")
	val kerangkaPemikiran: String,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("jadwal_kegiatan")
	val jadwalKegiatan: String,

	@field:SerializedName("level_dosen")
	val levelDosen: String
)
