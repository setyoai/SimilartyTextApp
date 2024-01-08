package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("login_result")
	val loginResult: LoginResult,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class LoginResult(

	@field:SerializedName("id_mhs")
	val idMhs: String? = null,

	@field:SerializedName("nama_mhs")
	val namaMhs: String? = null,

	@field:SerializedName("email_mhs")
	val emailMhs: String? = null,

	@field:SerializedName("nim_mhs")
	val nimMhs: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
