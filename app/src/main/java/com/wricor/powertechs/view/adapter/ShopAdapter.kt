package com.wricor.powertechs.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wricor.powertechs.R
import com.wricor.powertechs.model.Products

class ShopAdapter(val productList: List<Products>): RecyclerView.Adapter<ShopAdapter.ViewHolder>() {
    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_shop, ViewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val itemImage = ItemView.findViewById<ImageView>(R.id.image)
        val itemTitle = ItemView.findViewById<TextView>(R.id.title)
        val itemPrice = ItemView.findViewById<TextView>(R.id.price)

        fun render(productModel: Products) {
            Glide.with(itemImage.context).load(productModel.image).into(itemImage)
            itemTitle.text = productModel.product
            itemPrice.text = productModel.price
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int){
        val item  = productList[i]
        viewHolder.render(item)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
