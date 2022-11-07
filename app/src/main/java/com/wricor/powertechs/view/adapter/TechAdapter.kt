package com.wricor.powertechs.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wricor.powertechs.R

class TechAdapter: RecyclerView.Adapter<TechAdapter.ViewHolder>() {
    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_tech, ViewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrecio: TextView

        init {
            itemImage = ItemView.findViewById(R.id.image)
            itemTitle = ItemView.findViewById(R.id.title)
            itemPrecio = ItemView.findViewById(R.id.precio)
        }
    }

    val titles = arrayOf("Computador a", "Computador b", "Computador c", "Monitor a", "Monitor b", "Monitor c")
    val precio = arrayOf("$ 1.180.000", "$ 1.200.000", "$ 1.450.000", "$ 680.000", "$ 700.000", "$ 550.000")
    val image = arrayOf(R.drawable.pr_pca, R.drawable.pr_pcb, R.drawable.pr_pcc, R.drawable.pr_displaya, R.drawable.pr_displayb, R.drawable.pr_displayc)

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int){
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemPrecio.text = precio[i]
        viewHolder.itemImage.setImageResource(image[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}
