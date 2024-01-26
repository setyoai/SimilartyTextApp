package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DafSkripsiResponse(

	@field:SerializedName("upload_result")
	val uploadResult: UploadResultDaf,

	@field:SerializedName("error")
	val error: Boolean ,

	@field:SerializedName("message")
	val message: String? = null
)

data class UploadResultDaf(

	@field:SerializedName("id_dafskripsi")
	val idDafskripsi: Int? = null,
)
