package com.wricor.powertechs.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wricor.powertechs.R
import com.wricor.powertechs.view.adapter.TechAdapter

class ProductsFragment : Fragment() {
    lateinit var recyclerProd: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        recyclerProd = view.findViewById(R.id.recyclerview)
        val adapter = TechAdapter()
        recyclerProd.layoutManager = LinearLayoutManager(context)
        recyclerProd.adapter = adapter
        // Inflate the layout for this fragment
        return view
    }
}