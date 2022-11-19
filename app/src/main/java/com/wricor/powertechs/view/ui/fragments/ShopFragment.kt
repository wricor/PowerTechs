package com.wricor.powertechs.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import com.wricor.powertechs.R

class ShopFragment : Fragment() {
    lateinit var recyclerProd: RecyclerView

    //lateinit var shopList: MutableList<Products>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        //recyclerProd = view.findViewById(R.id.recyclerview)
        //Toast.makeText(context, "Shop: " + shopList[0], Toast.LENGTH_SHORT).show()

        return view
    }
}