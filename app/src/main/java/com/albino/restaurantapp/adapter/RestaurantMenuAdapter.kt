package com.albino.restaurantapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albino.restaurantapp.R
import com.albino.restaurantapp.model.RestaurantMenu

class RestaurantMenuAdapter(context:Context,val restaurantMenu:ArrayList<RestaurantMenu>):RecyclerView.Adapter<RestaurantMenuAdapter.ViewHolderRestaurantMenu>() {

    class ViewHolderRestaurantMenu(view:View):RecyclerView.ViewHolder(view){
        val textViewSerialNumber:TextView=view.findViewById(R.id.textViewSerialNumber)
        val textViewItemName:TextView=view.findViewById(R.id.textViewItemName)
        val textViewItemPrice:TextView=view.findViewById(R.id.textViewItemPrice)
        val buttonAddToCart:TextView=view.findViewById(R.id.buttonAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRestaurantMenu {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.restaurant_menu_recycler_view_single_row,parent,false)
        return ViewHolderRestaurantMenu(view)
    }

    override fun getItemCount(): Int {
        restaurantMenu.size
        return restaurantMenu.size
    }

    override fun onBindViewHolder(holder: ViewHolderRestaurantMenu, position: Int) {
        val restaurantMenuItem=restaurantMenu[position]
        holder.buttonAddToCart.setOnClickListener(View.OnClickListener {

            if(holder.buttonAddToCart.getTag().toString().equals("true"))
            {

                holder.buttonAddToCart.setTag("false")

                holder.buttonAddToCart.setBackgroundColor(Color.GRAY)
            }
            else
            {
                holder.buttonAddToCart.setTag("true")

                holder.buttonAddToCart.setBackgroundColor(Color.rgb(178, 42, 0))

            }


        })



        holder.textViewItemName.setTag(restaurantMenuItem.id+"")
        holder.textViewSerialNumber.text=restaurantMenuItem.id+""
        holder.textViewItemName.text=restaurantMenuItem.name
        holder.textViewItemPrice.text="Rs."+restaurantMenuItem.cost_for_one



    }

}