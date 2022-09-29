package com.android.a27092022

import retrofit2.Call
import retrofit2.http.GET


interface NetworkApi {
    @GET("posts")
     fun getPosts():Call<List<ModelItem>>
}