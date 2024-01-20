package com.setyo.similartytextapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.setyo.similartytextapp.data.remote.response.UserData

data class UpdateUserResponse(

    @field:SerializedName("user_data")
	val userData: UserData,

    @field:SerializedName("error")
	val error: Boolean,

    @field:SerializedName("message")
	val message: String,

    @field:SerializedName("status")
	val status: Int
)

data class UserUpdate(

	@field:SerializedName("nama_mhs")
	val namaMhs: String,

	@field:SerializedName("alamat_mhs")
	val alamatMhs: String,

	@field:SerializedName("nim_mhs")
	val nimMhs: String,

	@field:SerializedName("id_mhs")
	val idMhs: String
)
