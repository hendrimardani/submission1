package com.example.mysubmission11.data.response

import com.google.gson.annotations.SerializedName

data class UserDetail(

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("followers")
	val followers: Int? = null,

	@field:SerializedName("following")
	val following: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,
)
