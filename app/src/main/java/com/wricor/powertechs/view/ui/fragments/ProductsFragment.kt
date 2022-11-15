package com.wricor.powertechs.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wricor.powertechs.R
import com.wricor.powertechs.view.adapter.ProductsAdapter
import com.wricor.powertechs.viewmodel.ProductsViewModel

class ProductsFragment : Fragment() {
    lateinit var recyclerProd: RecyclerView

    lateinit var firebaseAuth: FirebaseAuth

    lateinit var adapter: ProductsAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(ProductsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        //val btnProductsAdd =
        recyclerProd = view.findViewById(R.id.recyclerview)
        adapter = ProductsAdapter(requireContext())
        recyclerProd.layoutManager = LinearLayoutManager(context)
        recyclerProd.adapter = adapter
        observeData()

        adapter.onItemClick = {
            Toast.makeText(requireContext(), "Product", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    fun observeData(){
        viewModel.productData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}