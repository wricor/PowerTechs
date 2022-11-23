package com.wricor.powertechs.view.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.wricor.powertechs.R
import com.wricor.powertechs.model.Shop
import com.wricor.powertechs.view.adapter.OnShopItemClickListener
import com.wricor.powertechs.view.adapter.ShopAdapter
import com.wricor.powertechs.viewmodel.ShopsViewModel
import java.text.DecimalFormat
import kotlin.math.round

class ShopFragment : Fragment(), OnShopItemClickListener {
    lateinit var recyclerShop: RecyclerView

    lateinit var firebaseAuth: FirebaseAuth
    val database: FirebaseFirestore = FirebaseFirestore.getInstance()

    lateinit var adapter: ShopAdapter
    private val viewModel by lazy {ViewModelProvider(this).get(ShopsViewModel::class.java)}

    lateinit var txtCompraTotal: TextView
    lateinit var txtCompraSubtotal: TextView
    lateinit var txtCompraIva: TextView
    lateinit var btnCompraPagar: Button

    //lateinit var shopList: MutableList<Products>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        recyclerShop = view.findViewById(R.id.recyclerview)
        txtCompraTotal = view.findViewById(R.id.txt_compra_total)
        txtCompraSubtotal = view.findViewById(R.id.txt_compra_subtotal)
        txtCompraIva = view.findViewById(R.id.txt_compra_iva)
        btnCompraPagar = view.findViewById(R.id.btn_compra_pagar)
        adapter = ShopAdapter(requireContext(), this)
        recyclerShop.layoutManager = LinearLayoutManager(context)
        recyclerShop.adapter = adapter
        observeData()
        calculateValues()
        btnCompraPagar.setOnClickListener {
            endShop()
        }
       //recyclerProd = view.findViewById(R.id.recyclerview)
        //Toast.makeText(context, "Shop: " + shopList[0], Toast.LENGTH_SHORT).show()

        return view
    }

    private fun observeData() {
        viewModel.fetchShopsData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun calculateValues() {
        database.collection("compras")
            .get()
            .addOnSuccessListener { result ->
                val unitPrice = mutableListOf<String>()
                for (document in result) {
                    val price = document["price"].toString()
                    unitPrice.add(price!!)
                }
                val totalPrice = unitPrice.mapNotNull { it.toIntOrNull() }.sum()
                val subtotalPrice = round(totalPrice / 1.19)
                val ivaPrice = totalPrice - subtotalPrice
                txtCompraTotal.setText(Integer.toString(totalPrice))
                txtCompraIva.setText(Integer.toString(ivaPrice.toInt()))
                txtCompraSubtotal.setText(Integer.toString(subtotalPrice.toInt()))
            }
    }

    private fun endShop() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Efectuar la compra")
        builder.setMessage("¿Desea realizar la compra?")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            findNavController().navigate(R.id.requestsFragment)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }
    override fun onItemClick(product: Shop, position: Int) {

    }
}