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
	val levelDosen: String
)
