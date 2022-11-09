package com.example.retrofitdemo

import retrofit2.Call
import retrofit2.http.GET


interface Methods {

    @GET("users?page=2")
    fun getAllData(): Call<Model>

}