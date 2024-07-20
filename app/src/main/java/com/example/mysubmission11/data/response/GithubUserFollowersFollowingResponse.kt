package com.example.mysubmission11.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserFollowersResponse(

	@field:SerializedName("GithubUserFollowersResponse")
	val userFollowersFollowing: List<UserFollowersFollowing>
)

data class UserFollowersFollowing(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String
)
