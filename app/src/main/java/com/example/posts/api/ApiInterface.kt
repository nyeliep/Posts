package com.example.posts.api

import retrofit2.Call
import retrofit2.http.GET
import com.example.posts.model.PostResponse


interface ApiInterface {
    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>
}


