package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginUserResponse(

	@field:SerializedName("login_result")
	val loginResult: LoginResultUser, 

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String? = null
)

data class LoginResultUser(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("id_dosen")
	val idDosen: String? = null,

	@field:SerializedName("username_user")
	val usernameUser: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
