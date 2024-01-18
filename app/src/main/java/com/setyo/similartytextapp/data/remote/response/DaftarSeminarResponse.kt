package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DaftarSeminarResponse(

	@field:SerializedName("upload_result")
	val uploadResult: UploadResultSkripsi? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class UploadResultSkripsi(

	@field:SerializedName("pengesahan_dafsempro")
	val pengesahanDafsempro: String? = null,

	@field:SerializedName("transkrip_dafsempro")
	val transkripDafsempro: String? = null,

	@field:SerializedName("bukubimbingan_dafsempro")
	val bukubimbinganDafsempro: String? = null,

	@field:SerializedName("kwkomputer_dafsempro")
	val kwkomputerDafsempro: String? = null,

	@field:SerializedName("status_dafsempro")
	val statusDafsempro: Any? = null
)
