package com.setyo.similartytextapp.model

data class UserModel (
    val id_mhs: String,
    val nim_mhs: String,
    val nama_mhs: String,
    val token: String,
    val role: String,
    val isLogin: Boolean
)
