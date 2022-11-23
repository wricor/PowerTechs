package com.wricor.powertechs.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wricor.powertechs.R
import com.wricor.powertechs.model.Requests

class RequestAdapter(val requestList: List<Requests>): RecyclerView.Adapter<RequestAdapter.ViewHolder>() {
    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_requests, ViewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val itemImage = ItemView.findViewById<ImageView>(R.id.image)
        val itemTitle = ItemView.findViewById<TextView>(R.id.title)
        val itemPrice = ItemView.findViewById<TextView>(R.id.price)
        //val itemId = ItemView.findViewById<Button>(R.id.idProducto)

        fun render(requestModel: Requests) {
            itemTitle.id = requestModel.id
            itemPrice.text = requestModel.value
            //itemId.id = productModel.id
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int){
        //when(viewHolder){
        //    is ViewHolder -> viewHolder.render(productList[i])
        //}
        val item  = requestList[i]
        viewHolder.render(item)
    }

    override fun getItemCount(): Int {
        return requestList.size
    }
}
