package com.example.pw5.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pw5.R
import com.example.pw5.database.Product
import kotlinx.coroutines.withContext

class ListAdapter(): RecyclerView.Adapter<com.example.pw5.adapter.ListAdapter.MyViewHolder>() {
    private var productList: List<Product> = emptyList()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val context = itemView.context
        val image: ImageView = itemView.findViewById(R.id.imageView)
        val information_bd: TextView = itemView.findViewById(R.id.information_bd)
        val rating: TextView = itemView.findViewById(R.id.rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.context).load(productList[position].images).into(holder.image)

        val pattern = "%s\n\n%s\n%s\n\n%s\n%s"

        holder.information_bd.text = String.format(pattern, productList[position].title,
            productList[position].category, productList[position].brand,
            productList[position].price.toString(), productList[position].returnPolicy)
        holder.rating.text = productList[position].rating.toString()
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(productList: List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }
}