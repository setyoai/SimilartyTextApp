package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class UpdatePenilaianResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("detsempro_update")
	val detSemproUpdate: DetSemproUpdate
)

data class DetSemproUpdate(

	@field:SerializedName("ketrev_detsempro")
	val ketrevDetsempro: String
)
