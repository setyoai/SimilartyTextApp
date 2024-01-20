package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.setyo.similartytextapp.data.remote.response.UserData

data class UserResponse(

    @field:SerializedName("user_data")
	val userData: UserData,

    @field:SerializedName("error")
	val error: Boolean? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("status")
	val status: Int? = null
)

data class UserData(

	@field:SerializedName("nama_mhs")
	val namaMhs: String? = null,

	@field:SerializedName("nim_mhs")
	val nimMhs: String? = null,

	@field:SerializedName("id_mhs")
	val idMhs: String? = null,

	@field:SerializedName("alamat_mhs")
	val alamatMhs: String? = null,

	@field:SerializedName("nohp_mhs")
	val nohpMhs: String? = null,

	@field:SerializedName("email_mhs")
	val emailMhs: String? = null,

	@field:SerializedName("photo_mhs")
	val photoMhs: String? = null
)
