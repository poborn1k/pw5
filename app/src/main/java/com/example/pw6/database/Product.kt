package com.example.pw6.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val brand: String,
    val returnPolicy: String,
    val images: String
)