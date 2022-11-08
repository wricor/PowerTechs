package com.wricor.powertechs.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wricor.powertechs.R
import com.wricor.powertechs.model.Products
import com.wricor.powertechs.view.adapter.ShopAdapter

class ShopFragment : Fragment() {
    lateinit var recyclerProd: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        recyclerProd = view.findViewById(R.id.recyclerview)
        val product = listOf<Products>(
            Products(
                1,
                "Computador aa",
                "$ 1.180.000",
                R.drawable.pr_pca
            ),
            Products(
                2,
                "Computador bb",
                "$ 1.200.000",
                R.drawable.pr_pcb
            ),
            Products(
                3,
                "Computador cc",
                "$ 1.450.000",
                R.drawable.pr_pcc
            ),
            Products(
                4,
                "Monitor aa",
                "$ 680.000",
                R.drawable.pr_displaya
            ),
            Products(
                5,
                "Monitor bb",
                "$ 700.000",
                R.drawable.pr_displayb
            ),
            Products(
                6,
                "Monitor cc",
                "$ 550.000",
                R.drawable.pr_displayc
            )
        )
        var productsMutableList: MutableList<Products> = product.toMutableList()
        //val titles = arrayOf("Computador aa", "Computador bb", "Computador cc", "Monitor aa", "Monitor bb", "Monitor cc")
        //val price = arrayOf("$ 1.180.000", "$ 1.200.000", "$ 1.450.000", "$ 680.000", "$ 700.000", "$ 550.000")
        //val image = arrayOf(R.drawable.pr_pca, R.drawable.pr_pcb, R.drawable.pr_pcc, R.drawable.pr_displaya, R.drawable.pr_displayb, R.drawable.pr_displayc)

        val adapter = ShopAdapter(productsMutableList)
        recyclerProd.layoutManager = LinearLayoutManager(context)
        recyclerProd.adapter = adapter
        // Inflate the layout for this fragment
        return view
    }
}