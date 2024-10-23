package com.example.pw6.koin

import com.example.pw6.database.ProductViewModel
import com.example.pw6.retrofit.ProductAPI
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val shopModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductAPI::class.java)
    }
    viewModel { ProductViewModel(application = get()) }
}