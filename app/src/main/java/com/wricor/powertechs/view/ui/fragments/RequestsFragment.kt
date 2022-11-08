package com.wricor.powertechs.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wricor.powertechs.R
import com.wricor.powertechs.model.Requests
import com.wricor.powertechs.view.adapter.RequestAdapter

class RequestsFragment : Fragment() {
    lateinit var recyclerProd: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_requests, container, false)
        recyclerProd = view.findViewById(R.id.recyclerviewreq)
        val request = listOf<Requests>(
            Requests(1,"$ 1.180.000"),
            Requests(2, "$ 1.200.000"),
            Requests(3, "$ 1.450.000"),
            Requests(4, "$ 680.000"),
            Requests(5, "$ 700.000"),
            Requests(6, "$ 550.000")
        )
        var requestsMutableList: MutableList<Requests> = request.toMutableList()
        //val titles = arrayOf("Computador aa", "Computador bb", "Computador cc", "Monitor aa", "Monitor bb", "Monitor cc")
        //val price = arrayOf("$ 1.180.000", "$ 1.200.000", "$ 1.450.000", "$ 680.000", "$ 700.000", "$ 550.000")
        //val image = arrayOf(R.drawable.pr_pca, R.drawable.pr_pcb, R.drawable.pr_pcc, R.drawable.pr_displaya, R.drawable.pr_displayb, R.drawable.pr_displayc)

        val adapter = RequestAdapter(requestsMutableList)
        recyclerProd.layoutManager = LinearLayoutManager(context)
        recyclerProd.adapter = adapter
        // Inflate the layout for this fragment
        return view
    }
}