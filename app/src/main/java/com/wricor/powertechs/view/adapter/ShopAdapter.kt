package com.wricor.powertechs.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wricor.powertechs.R
import com.wricor.powertechs.model.Shop

class ShopAdapter(private val context: android.content.Context, var clickListener: OnShopItemClickListener): RecyclerView.Adapter<ShopAdapter.ViewHolder>() {
    private var productsList = mutableListOf<Shop>()

    fun setListData(data: MutableList<Shop>) {
        productsList = data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_shop, ViewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        fun binWeb(prod: Shop, action: OnShopItemClickListener) {
            itemView.findViewById<TextView>(R.id.product).text = prod.product
            itemView.findViewById<TextView>(R.id.price).text = prod.price
            Picasso.with(itemView.context).load(prod.image).into(itemView.findViewById<ImageView>(R.id.image))
            //val btnProductsDelete = itemView.findViewById<Button>(R.id.btn_products_delete)
            //btnProductsDelete.setOnClickListener {
            //    action.onItemClick(prod, adapterPosition)
            //}
        //val itemImage = ItemView.findViewById<ImageView>(R.id.image)
        //val itemTitle = ItemView.findViewById<TextView>(R.id.title)
        //val itemPrice = ItemView.findViewById<TextView>(R.id.price)

        //fun render(productModel: Products) {
        //    Glide.with(itemImage.context).load(productModel.image).into(itemImage)
        //    itemTitle.text = productModel.product
        //    itemPrice.text = productModel.price
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int){
        val product = productsList[i]
        viewHolder.binWeb(product, clickListener)
    }

    override fun getItemCount(): Int {
        return if (productsList.size > 0) {
            productsList.size
        } else {
            0
        }
    }
}

interface OnShopItemClickListener {
    fun onItemClick(prod: Shop, position: Int)
}
