package com.example.pw6.retrofit

data class ProductJSON (
    val title: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val brand: String,
    val returnPolicy: String,
    val images: List<String>
)