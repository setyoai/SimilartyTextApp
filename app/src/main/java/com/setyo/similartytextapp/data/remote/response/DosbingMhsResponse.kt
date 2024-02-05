package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DosbingMhsResponse(

	@field:SerializedName("dosbingmhs_data")
	val dosbingmhsData: DosbingmhsData,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class DosbingmhsData(

	@field:SerializedName("dosbing_id")
	val dosbingId: String,

	@field:SerializedName("dosen1_dosbing")
	val dosen1Dosbing: String,

	@field:SerializedName("nama1_dosbing")
	val nama1Dosbing: String,

	@field:SerializedName("dosen2_dosbing")
	val dosen2Dosbing: String,

	@field:SerializedName("nama2_dosbing")
	val nama2Dosbing: String,

	@field:SerializedName("dafskripsiid_dosbing")
	val dafskripsiidDosbing: String
)
