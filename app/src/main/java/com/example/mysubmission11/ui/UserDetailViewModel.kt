package com.example.mysubmission11.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysubmission11.data.response.UserDetail
import com.example.mysubmission11.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel(application: Application): AndroidViewModel(application) {

    companion object {
        private const val TAG = "TEST HASIL RESPONSE"
    }

    private val _namaQueryUser = MutableLiveData<UserDetail>()
    val namaQueryUser: LiveData<UserDetail> = _namaQueryUser

    init {
        findUserDetail(namaQueryUser.toString())
    }

    fun findUserDetail(queryUser: String) {
        val client = ApiConfig.getApiService().getDetailUser(queryUser)
        client.enqueue(object : Callback<UserDetail> {
            override fun onResponse(
                call: Call<UserDetail>,
                response: Response<UserDetail>
            ) {
                if (response.isSuccessful) _namaQueryUser.value = response.body()
                else Log.e(TAG, "onFailure : ${response.message()}")
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message}")
            }
        })
    }
}