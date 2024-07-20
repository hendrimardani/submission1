package com.example.mysubmission11.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserFollowersResponse(

	@field:SerializedName("GithubUserFollowersResponse")
	val userFollowers: List<UserFollowers>
)

data class UserFollowers(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String
)
