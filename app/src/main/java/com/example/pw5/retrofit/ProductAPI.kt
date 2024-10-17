package com.example.pw5.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {
    @GET("/products/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductJSON
}