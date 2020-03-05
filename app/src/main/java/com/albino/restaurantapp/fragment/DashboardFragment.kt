package com.albino.restaurantapp.fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.albino.restaurantapp.R
import com.albino.restaurantapp.adapter.DashboardFragmentAdapter
import com.albino.restaurantapp.model.Restaurant

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {


    lateinit var recyclerView:RecyclerView
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var dashboardAdapter:DashboardFragmentAdapter

    var restaurantInfoList= arrayListOf<Restaurant>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)

        layoutManager=LinearLayoutManager(activity)//set the layout manager

        recyclerView=view.findViewById(R.id.recyclerViewDashboard)//recycler view from Dashboard fragment

        dashboardAdapter= DashboardFragmentAdapter(activity as Context,restaurantInfoList)//set the adapter with the data

        recyclerView.adapter=dashboardAdapter//bind the  recyclerView to the adapter

        recyclerView.layoutManager=layoutManager //bind the  recyclerView to the layoutManager

        return view
    }


}
