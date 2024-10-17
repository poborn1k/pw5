package com.example.pw5.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class ProductsDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        fun getDatabase(context: Context): ProductsDatabase {
            val tmp = INSTANCE

            if (tmp != null) {
                return tmp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "product_database.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun amazonProductDao(): ProductDao

}