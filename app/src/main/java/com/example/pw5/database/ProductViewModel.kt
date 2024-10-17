package com.example.pw5.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Product>>
    private val repository: ProductRepository =
        ProductRepository(ProductsDatabase.getDatabase(application).amazonProductDao())

    init {
        readAllData = repository.readAllData
    }

    fun addProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }
}