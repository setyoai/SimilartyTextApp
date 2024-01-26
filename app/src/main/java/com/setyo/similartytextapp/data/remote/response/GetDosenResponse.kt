package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetDosenResponse(

	@field:SerializedName("user_details")
	val userDetails: UserDetails,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class UserDetails(

	@field:SerializedName("nama_dosen")
	val namaDosen: String,

	@field:SerializedName("id_user")
	val idUser: String,

	@field:SerializedName("username")
	val username: String
)
