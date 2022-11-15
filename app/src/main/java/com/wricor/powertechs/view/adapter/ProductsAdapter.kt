package com.wricor.powertechs.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wricor.powertechs.R
import com.wricor.powertechs.model.Products

class ProductsAdapter(private val context: android.content.Context): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private var productsList = mutableListOf<Products>()
    // Lista para agregar al carrito
    var shopList = mutableListOf<Products>()
    var onItemClick: ((Products) -> Unit)? = null

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
            //itemView.findViewById<Button>(R.id.btn_products_add).setOnClickListener { Toast.makeText(context, "Click add", Toast.LENGTH_SHORT) }
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int){
        val product = productsList[i]
        //Toast.makeText(context, "Entra a : " + i, Toast.LENGTH_LONG).show()
        viewHolder.binWeb(product)
        // Escucha de boton
        viewHolder.itemView.findViewById<Button>(R.id.btn_products_delete).isVisible = false
        viewHolder.itemView.findViewById<Button>(R.id.btn_products_add).setOnClickListener {
            //onItemClick?.invoke(product)
            Toast.makeText(context, "Click acá " + productsList[i], Toast.LENGTH_SHORT).show()
            shopList.add(productsList[i])
            Toast.makeText(context, "Elementos shop: " + shopList, Toast.LENGTH_SHORT).show()
            viewHolder.itemView.findViewById<Button>(R.id.btn_products_add).isVisible = false
            viewHolder.itemView.findViewById<Button>(R.id.btn_products_delete).isVisible = true
        }
        viewHolder.itemView.findViewById<Button>(R.id.btn_products_delete).setOnClickListener {
            //onItemClick?.invoke(product)
            Toast.makeText(context, "Borra acá " + productsList[i], Toast.LENGTH_SHORT).show()
            shopList.removeAll {it.equals(productsList[i])}
            Toast.makeText(context, "Elementos shop: " + shopList, Toast.LENGTH_SHORT).show()
            viewHolder.itemView.findViewById<Button>(R.id.btn_products_add).isVisible = true
            viewHolder.itemView.findViewById<Button>(R.id.btn_products_delete).isVisible = false
        }
    }

    override fun getItemCount(): Int {
        return if (productsList.size > 0) {
            productsList.size
        } else {
            0
        }
    }
}
