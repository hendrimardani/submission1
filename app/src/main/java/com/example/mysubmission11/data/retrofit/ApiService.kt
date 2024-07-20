package com.example.mysubmission11.data.retrofit

import com.example.mysubmission11.data.response.GithubUserFollowersResponse
import com.example.mysubmission11.data.response.GithubUserResponse
import com.example.mysubmission11.data.response.UserDetail
import com.example.mysubmission11.data.response.UserFollowers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getUsers(
        @Query("q") q: String
    ): Call<GithubUserResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<UserDetail>

    @GET("users/{username}/following")
    fun getFollowingUser(
        @Path("username") username: String
    ): Call<GithubUserFollowersResponse>

    @GET("users/{username}/followers")
    fun getFollowersUser(
        @Path("username") username: String
    ): Call<List<UserFollowers>>
}