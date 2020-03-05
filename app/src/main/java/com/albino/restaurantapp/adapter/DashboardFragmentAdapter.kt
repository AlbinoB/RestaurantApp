package com.albino.restaurantapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.albino.restaurantapp.R
import com.albino.restaurantapp.model.Restaurant

class DashboardFragmentAdapter(context:Context,val arrayList:ArrayList<Restaurant>):RecyclerView.Adapter<DashboardFragmentAdapter.ViewHolderDashboard>() {

    class ViewHolderDashboard(view:View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDashboard {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.dashboard_recycler_view_single_row,parent,false)
        return ViewHolderDashboard(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolderDashboard, position: Int) {
        /*val text = arrayList.get(position)
        holder.itemView.text=text  // save the data to all the view in the dashboard recycler view single row*/

    }
}