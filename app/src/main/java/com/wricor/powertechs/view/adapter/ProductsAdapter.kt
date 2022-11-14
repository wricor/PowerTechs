package com.wricor.powertechs.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wricor.powertechs.R
import com.wricor.powertechs.model.Products

class ProductsAdapter(private val context: android.content.Context): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private var productsList = mutableListOf<Products>()

    fun setListData(data: MutableList<Products>) {
        productsList = data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_products, ViewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        fun binWeb(prod: Products) {
            itemView.findViewById<TextView>(R.id.product).text = prod.product
            itemView.findViewById<TextView>(R.id.price).text = prod.price
            Picasso.with(itemView.context).load(prod.image).into(itemView.findViewById<ImageView>(R.id.image))
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int){
        val product = productsList[i]
        viewHolder.binWeb(product)
    }

    override fun getItemCount(): Int {
        return if (productsList.size > 0) {
            productsList.size
        } else {
            0
        }
    }
}
